package Web;

import Model.ItemList;
import Model.Parameters;
import Model.WatchedItem;

import java.util.List;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public interface IWebsiteManager {

    public Parameters getPriceParameters(WatchedItem item);

    /**
     * Called when adding a new item to the watchlist. Verifies address.
     * Note: Does NOT save price. Call update after this to do that.
     * @param item the item to be checked and added
     * @return the item with updated parameters. Null if error.
     */
    public WatchedItem getItemInitial(WatchedItem item);

    /**
     * Method to iterate through ItemList and get the updated prices for each
     * list item according to the saved parameters.
     */
    public void updateItemPrices();
}
