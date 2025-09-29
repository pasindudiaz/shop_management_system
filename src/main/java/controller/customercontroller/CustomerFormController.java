package controller.customercontroller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;


public class CustomerFormController implements Initializable {
    CustomerServiceController customerServiceController = new CustomerController();

    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn colemail;
    public TableView <Customer>tblview;
    public TableColumn colcusid;


    public TextField cusid;
    public TextField cusname;
    public TextField cusaddress;
    public TextField cusemail;

    public void AddCustomer(ActionEvent actionEvent) {
        customerServiceController.addCustomer(new Customer(cusid.getText(), cusname.getText(), cusaddress.getText(), cusemail.getText()));
        tblview.setItems(customerServiceController.getAllCustomerDetails());
    }

    public void UpdateCustomer(ActionEvent actionEvent) {
        customerServiceController.updateCustomer(cusid.getText(), cusname.getText(), cusaddress.getText(), cusemail.getText());
        tblview.setItems(customerServiceController.getAllCustomerDetails());
    }

    public void DeleteCustomer(ActionEvent actionEvent) {
        customerServiceController.deleteCustomer(cusid.getText());
        ClearCustomer(null);
        tblview.setItems(customerServiceController.getAllCustomerDetails());
    }

    public void ClearCustomer(ActionEvent actionEvent) {
        cusid.setText(null);
        cusname.setText(null);
        cusaddress.setText(null);
        cusemail.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colcusid.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblview.setItems(customerServiceController.getAllCustomerDetails());

        tblview.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                cusid.setText(t1.getCusId());
                cusname.setText(t1.getName());
                cusaddress.setText(t1.getAddress());
                cusemail.setText(t1.getEmail());
            }
        }
        ));
    }

}



