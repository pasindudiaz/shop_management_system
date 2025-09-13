package controller.ordercontroller;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetails;

public interface OrderServiceController {

    Customer searchCustomer(String cusid);
    Item searchItem(String itemId);
    void addOrder(Order order);
    void addOrderDetails(OrderDetails orderDetails);
    void  updateQuantity(Integer quantity,String itemId );
    ObservableList<Order> getAllOrders();
}
