package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michael.gardanier on 6/6/17.
 */
class ItemListTest {

    @BeforeEach
    void reset(){
        ItemList.getInstance().clearList();
    }

    @Test
    void addItem() {
        assertEquals(0, ItemList.getInstance().getSize());

        ItemList.getInstance().addItem(new WatchedItem("test", "item1"));
        assertTrue(ItemList.getInstance().getSize() == 1);
        WatchedItem item = ItemList.getInstance().getNextWatchedItem();
        assertEquals(0, ItemList.getInstance().getSize());
        assertEquals("test", item.getItemURL());
        assertEquals("item1", item.getItemName());

        for(int i = 0; i < 50; i++){
            WatchedItem item1 = new WatchedItem("item" + i, "");
            ItemList.getInstance().addItem(item1);
        }

        assertEquals(50, ItemList.getInstance().getSize());

    }

    @Test
    void getNextWatchedItem() {
        assertEquals(0, ItemList.getInstance().getSize());
        for(int i = 0; i < 50; i++){
            WatchedItem item1 = new WatchedItem("item" + i, "");
            ItemList.getInstance().addItem(item1);
        }

        assertEquals(50, ItemList.getInstance().getSize());

        for(int i = 0; ItemList.getInstance().getSize() > 0; i++){
            WatchedItem it = ItemList.getInstance().getNextWatchedItem();
            assertEquals("item" + i, it.getItemURL());
        }
    }

    @Test
    void deleteWatchedItem() {
        assertEquals(0, ItemList.getInstance().getSize());
        for(int i = 1; i <= 15; i++) {
            for(int j = 0; j < 50; j++){
                WatchedItem item1 = new WatchedItem("","item" + j);
                ItemList.getInstance().addItem(item1);
            }

            assertEquals(50, ItemList.getInstance().getSize());
            int target = i + 5;
            assertTrue(ItemList.getInstance().deleteWatchedItem("item" + target));
            while (ItemList.getInstance().getSize() > 0) {
                WatchedItem t = ItemList.getInstance().getNextWatchedItem();
                assertNotEquals("item" + target, t.getItemName());
            }
        }

    }

}