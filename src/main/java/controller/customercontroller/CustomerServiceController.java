package controller.customercontroller;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerServiceController {

    void addCustomer(Customer customer);
    void deleteCustomer(String cusid);
    ObservableList<Customer> getAllCustomerDetails();
    void updateCustomer(String cusid,String name,String email, String address);


}
