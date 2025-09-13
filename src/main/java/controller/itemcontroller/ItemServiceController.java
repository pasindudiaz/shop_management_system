package controller.itemcontroller;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemServiceController {

    void addItem(Item item);
    void deleteItem(String itemId);
    ObservableList<Item> getALLItemDetails();
    void updateItem(String itemid,String name, String description, String quantity, String unitprice);
}
