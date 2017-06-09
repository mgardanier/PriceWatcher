package Web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by michael.gardanier on 6/8/17.
 */
public class jsoupTest {

    public String getPriceFromPage(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        Elements prices = doc.select("[class*=price]");
        for(Element price : prices){
            String text = price.ownText();
            String tst;
            if(text.contains("$"))
                tst = "success";
            int val = price.siblingIndex();
        }
        return null;
    }
}
