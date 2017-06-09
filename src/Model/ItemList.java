package Model;

import FileIO.StatusLogger;

import java.util.List;
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

    /**
     * Method to pop off the next item. Note: Does NOT replace it.
     * If iterating through, add the item back in afterwards.
     * @return the next watched item on the queue.
     */
    public WatchedItem getNextWatchedItem(){
        try {
            return this.itemList.take();
        } catch (InterruptedException e) {
            StatusLogger.getInstance().logError(e.getMessage());
            return null;
        }
    }

    public boolean deleteWatchedItem(String itemName){
        boolean found = false;
        BlockingQueue<WatchedItem> temp = new LinkedBlockingQueue<>();
        while(!this.itemList.isEmpty()){
            WatchedItem t = getNextWatchedItem();
            if(t.getItemName().toLowerCase().equals(itemName.toLowerCase()))
                found = true;
            else
                temp.add(t);
        }

        this.itemList = temp;
        return found;
    }

    public boolean clearList(){
        this.itemList.clear();
        return true;
    }

    public int getSize(){
        return this.itemList.size();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        BlockingQueue<WatchedItem> temp = new LinkedBlockingQueue<>();
        int counter = 1;
        while(!this.itemList.isEmpty()){
            builder.append(counter + ".\t");
            WatchedItem item = getNextWatchedItem();
            builder.append(item.toString());
            temp.add(item);
            counter++;
        }
        itemList = temp;
        return builder.toString();
    }

    public void setInstance(ItemList list){
        this.instance = list;
    }

    public void setItemList(BlockingQueue<WatchedItem> list){
        this.itemList = list;
    }
}
