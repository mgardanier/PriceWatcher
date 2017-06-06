package Model;

import java.util.Date;

/**
 * Created by michael.gardanier on 6/6/17.
 */
public class WatchedItem {
    private double bestPrice;
    private double recentPrice;
    private String itemURL;
    private Date dateOfBestPrice;

    public WatchedItem(String itemURL){
        this.itemURL = itemURL;
    }

    public double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public double getRecentPrice() {
        return recentPrice;
    }

    public void setRecentPrice(double recentPrice) {
        this.recentPrice = recentPrice;
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
}
