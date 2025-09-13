package controller.orderdetailscontroller;

import javafx.collections.ObservableList;
import model.OrderDetails;

public interface OrderDetailsserviceController {

    ObservableList<OrderDetails> getAllOrderDetails();
    void deleteOrderDetails(String orderid);
    void updateOrderDetails(Integer quantity, Integer discount,String orderid);
}
