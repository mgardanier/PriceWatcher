package Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to deserialize json into and then load model.
 * Created by michael.gardanier on 6/8/17.
 */
public class ItemListLoader {

    private List<WatchedItem> itemList;

    public ItemListLoader(){}

    public void setItemList(List<WatchedItem> items){
        this.itemList = items;
    }

    /**
     * Method to load the items from this object into the correct
     * data structure in the model.
     */
    public void loadItemList(){
        for(WatchedItem item : itemList){
            ItemList.getInstance().addItem(item);
        }
    }
}
