package controller.ordercontroller;

import javafx.collections.ObservableList;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetails;

public interface OrderServiceController {

    Customer searchCustomer(String cusid);
    Item searchItem(String itemId);
    void addOrder( String orderid,String customerid, String date,String itemid,String quantity,String discount);
    void addOrderDetails(OrderDetails orderDetails);
    void  updateQuantity(Integer quantity,String itemId );
    ObservableList<Order> getAllOrders();
    void addCustomerDetails(String customerId , String name, String address , String email);
}
