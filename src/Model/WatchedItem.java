package Model;

import java.util.Date;

/**
 * Created by michael.gardanier on 6/6/17.
 */
public class WatchedItem {
    private double bestPrice;
    private double recentPrice;
    private String itemURL;
    private String itemName;
    private Date dateOfBestPrice;

    public WatchedItem(String itemURL, String itemName){
        this.itemURL = itemURL;
        this.itemName = itemName;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(itemName + "\t");
        builder.append(recentPrice + "\t");
        builder.append(bestPrice + "\t");
        builder.append(dateOfBestPrice.toString());
        builder.append("\n");
        return builder.toString();
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
