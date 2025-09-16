package controller.orderdetailscontroller;

import javafx.collections.ObservableList;
import model.OrderDetails;

public interface OrderDetailsserviceController {

    ObservableList<OrderDetails> getAllOrderDetails();
    void deleteOrderDetails(String orderid);

    void updateOrderDetails(Integer newquantity, Integer discount, String orderid, String itemid);

    OrderDetails searchOrderDetails(String orderId);

    void updateHigherItemQuantity(Integer changeValue, String itemId);

    void updateLowerItemQuantity(Integer changeValue, String itemId);
}
