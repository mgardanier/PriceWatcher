package Web;

import FileIO.StatusLogger;
import Model.ItemList;
import Model.Parameters;
import Model.WatchedItem;
import UserIO.IUserInputManager;
import UserIO.UserInputManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by michael.gardanier on 6/9/17.
 */
public class WebsiteManager implements IWebsiteManager {

    @Override
    public Parameters getPriceParameters(WatchedItem item) {
        List<String> toPresentToUser = new ArrayList<>();
        Elements allPossible;
        try {
            allPossible = this.getElements(item.getItemURL());
            new UserInputManager().displayToUser("\n---Please confirm the current price---\n\n");
            if(allPossible == null)
                return null;
            for(int i = 0; i < allPossible.size(); i++){
                if(i >= 15)
                    break;
                toPresentToUser.add(allPossible.get(i).ownText());
            }
        } catch (IOException e) {
            StatusLogger.getInstance().logError("URL doesn't exist");
            return null;
        }

        for(int i = 0; i < toPresentToUser.size(); i++){
            if(presentOptionToUser(toPresentToUser.get(i))){
                //This is the correct price value
                Parameters params = new Parameters();
                params.setValue(allPossible.get(i).ownText());
                String elementType = allPossible.get(i).tag().getName();
                params = fillInElementType(params, elementType);
                params = fillInElementSelector(params, allPossible.get(i));
                return params;
            }
        }
        return null;
    }

    private Parameters fillInElementSelector(Parameters inputParameters, Element element){
        Parameters outputParameters = new Parameters();
        outputParameters.setValue(inputParameters.getValue());
        outputParameters.setElementType(inputParameters.getElementType());

        if(!element.id().equals("")){
            outputParameters.setSelectorValue(element.id());
            outputParameters.setSelector(Parameters.Selector.ID);
        } else if(!element.className().equals("")){
            outputParameters.setSelectorValue(element.className());
            outputParameters.setSelector(Parameters.Selector.CLASS);
        } else {
            outputParameters.setSelector(Parameters.Selector.OTHER);
            StatusLogger.getInstance().logError("Unrecognized selector");
        }
        return outputParameters;
    }

    private Parameters fillInElementType(Parameters inputParams, String elementType){
        Parameters outputParams = new Parameters();
        outputParams.setValue(inputParams.getValue());
        if(elementType.equals("span")){
            outputParams.setElementType(Parameters.ElementType.SPAN);
        } else if(elementType.equals("p")){
            outputParams.setElementType(Parameters.ElementType.P);
        } else {
            outputParams.setElementType(Parameters.ElementType.OTHER);
            StatusLogger.getInstance().logError("Unrecognized element type: " + elementType);
        }
        return outputParams;
    }

    @Override
    public WatchedItem getItemInitial(WatchedItem inputItem) {
        Parameters priceParams = getPriceParameters(inputItem);
        if(priceParams != null){
            WatchedItem item = new WatchedItem(inputItem.getItemURL(), inputItem.getItemName());
            item.setQueryParams(priceParams);
            item.setDateOfBestPrice(new Date());
            return item;
        }
        return null;

    }

    @Override
    public void updateItemPrices() {
        int size = ItemList.getInstance().getSize();
        for(int i = 0; i < size; i++) {
            WatchedItem item = ItemList.getInstance().getNextWatchedItem();
            try {
                item.setRecentPrice(getUpdatedPrice(item));
            } catch (IOException e) {
                StatusLogger.getInstance().logError("Error updating price for: " + item.getItemName());
            }
            ItemList.getInstance().addItem(item);
        }
    }

    public double getUpdatedPrice(WatchedItem inputItem) throws IOException {
        Document doc = Jsoup.connect(inputItem.getItemURL()).get();
        String priceStr;
        switch (inputItem.getQueryParams().getSelector()) {
            case CLASS:
                priceStr = selectOnClass(doc, inputItem.getQueryParams().getSelectorValue());
                break;
            case ID:
                priceStr = selectOnID(doc, inputItem.getQueryParams().getSelectorValue());
                break;
            default:
                return -1;
        }
        try {
            priceStr = priceStr.replaceAll("[$]", "");
            return parsePriceToDouble(priceStr);
        } catch (ParseException e) {
            StatusLogger.getInstance().logError("Price parsing error");
            return -1;
        }
    }

    public double parsePriceToDouble(String priceStr) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = format.parse(priceStr);
        return number.doubleValue();
    }

    private String selectOnClass(Document doc, String className){
        Elements els = doc.select("[class=" + className);
        return els.get(0).ownText();
    }

    private String selectOnID(Document doc, String id){
        Elements els = doc.select("[id=" + id);
        return els.get(0).ownText();
    }

    private String selectOnItemProp(Document doc, String id){
        Elements els = doc.select("[itemprop=" + id);
        return els.get(0).ownText();
    }

    private boolean presentOptionToUser(String price){
       IUserInputManager userInputManager = new UserInputManager();
       String response;
       while(true) {
           userInputManager.displayToUser("Is ( " + price + " ) the current price (y/n)? \n% ");
           response = userInputManager.getUserInput();
           if (response.toLowerCase().equals("y") || response.toLowerCase().equals("n"))
               break;
           else
               userInputManager.displayToUser("\nInvalid response.Try again.\n");
       }
       return response.toLowerCase().equals("y");

    }

    private Elements getElements(String url) throws IOException {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements prices = doc.select("[class*=price], [id*=price]");
            return prices;
        } catch (MalformedURLException e){
            StatusLogger.getInstance().logError("Bad URL");
            new UserInputManager().displayToUser("Bad URL.");
            return null;
        }
    }
}
