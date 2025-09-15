package controller.ordercontroller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Order;
import model.OrderDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public TextField orderid;
    public TextField itemid;
    public TextField quantity;
    public TextField discount;
    public TableColumn colorderid;
    public TableColumn colcustomerid;
    public TableColumn colorderdate;
    public TableColumn coltotal;
    public TableView<Order> tblview;
    public DatePicker date;
    OrderController orderController = new OrderController();

    public TextField email;
    public TextField address;
    public TextField name;
    public TextField customerid;

    public void searchCustomer(ActionEvent actionEvent) {
        Customer customer = orderController.searchCustomer(customerid.getText());
        clearOrder(null);
        customerid.setText(customer.getCusId());
        name.setText(customer.getName());
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());

    }

    public void clearOrder(ActionEvent actionEvent) {
        orderid.setText(null);
        itemid.setText(null);
        quantity.setText(null);
        discount.setText(null);
        date.setValue(null);
        name.setText(null);
        address.setText(null);
        email.setText(null);

    }

    public void addOrder(ActionEvent actionEvent) {
        orderController.addCustomerDetails(customerid.getText(),name.getText(),address.getText(),email.getText());
        orderController.addOrder(orderid.getText(),customerid.getText(), String.valueOf(date.getValue()),itemid.getText(),quantity.getText(),discount.getText());
        orderController.addOrderDetails(new OrderDetails(orderid.getText(),itemid.getText(),Integer.parseInt(quantity.getText()),Integer.parseInt(discount.getText())));
        orderController.updateQuantity(Integer.parseInt(quantity.getText()),itemid.getText());

        tblview.setItems(orderController.getAllOrders());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colcustomerid.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colorderdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tblview.setItems(orderController.getAllOrders());


    }
}
