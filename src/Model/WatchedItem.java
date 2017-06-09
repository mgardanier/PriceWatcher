package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michael.gardanier on 6/6/17.
 */
public class WatchedItem {
    private double bestPrice = Double.POSITIVE_INFINITY;
    private double recentPrice;
    private String itemURL;
    private String itemName;
    private Date dateOfBestPrice;
    private boolean lowerPrice;
    private Parameters queryParams;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    private String highlight = ANSI_GREEN_BACKGROUND + ANSI_BLACK;

    public WatchedItem(String itemURL, String itemName){
        this.itemURL = itemURL;
        this.itemName = itemName;
        bestPrice = Double.POSITIVE_INFINITY;
    }

    @Override
    public String toString(){
        String dateString;
        if(dateOfBestPrice != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateString = dateFormat.format(dateOfBestPrice);
        } else
            dateString = "N/A";
        String bPrice = String.format("%.2f", bestPrice);
        String rPrice = String.format("%.2f", recentPrice);
        String lineItem;
        if(lowerPrice) {
            lineItem = String.format(highlight + "%-25s \t$%-10s \t$%-12s %-15s" + ANSI_RESET + "\n", itemName, rPrice, bPrice, dateString);
            this.lowerPrice = false;
        } else
            lineItem = String.format("%-25s \t$%-10s \t$%-12s %-15s\n", itemName, rPrice, bPrice, dateString);

        return lineItem;
    }

    public double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(double bestPrice) {
        if(this.bestPrice > bestPrice)
            this.lowerPrice = true;
        this.bestPrice = bestPrice;
        this.setDateOfBestPrice(new Date());
    }

    public double getRecentPrice() {
        return recentPrice;
    }

    public void setRecentPrice(double recentPrice) {
        this.recentPrice = recentPrice;
        if(recentPrice <= bestPrice || bestPrice == 0)
            setBestPrice(recentPrice);
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public Date getDateOfBestPrice() {
        return dateOfBestPrice;
    }

    public void setDateOfBestPrice(Date dateOfBestPrice) {
        this.dateOfBestPrice = dateOfBestPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(boolean lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public Parameters getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Parameters queryParams) {
        this.queryParams = queryParams;
    }
}
