package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by michael.gardanier on 6/6/17.
 */
public class ItemList {

    private BlockingQueue<WatchedItem> itemList;
    private static ItemList instance;

    public static ItemList getInstance(){
        if(instance == null)
            instance = new ItemList();
        return instance;
    }

    private ItemList(){
        itemList = new LinkedBlockingQueue();
    }

    public void addItem(WatchedItem item){
        this.itemList.add(item);
    }

    public WatchedItem getNextWatchedItem(){
        try {
            return this.itemList.take();
        } catch (InterruptedException e) {
            StatusLogger.getInstance();
        }
    }
}
