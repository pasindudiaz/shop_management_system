package controller.orderdetailscontroller;

import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetails;

public interface OrderDetailsserviceController {

    ObservableList<OrderDetails> getAllOrderDetails();

    void deleteOrderDetails(String orderid);

    void updateOrderDetails(Integer newquantity, Integer discount, String orderid, String itemid);

    OrderDetails searchOrderDetails(String orderId);

    void updateHigherItemQuantity(Integer changeValue, String itemId);

    void updateLowerItemQuantity(Integer changeValue, String itemId);

    Item getItemQuantity(String itemId);

    void updateOrderTotal(String orderId, String itemId, Integer quantity, Integer discount);
}
