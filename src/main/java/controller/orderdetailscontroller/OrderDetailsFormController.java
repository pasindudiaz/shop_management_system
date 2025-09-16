package controller.orderdetailscontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsFormController implements Initializable {
    OrderDetailsController orderDetailsController = new OrderDetailsController();

    public TableView<OrderDetails> tblview;
    @FXML
    private TableColumn<?, ?> coldiscount;

    @FXML
    private TableColumn<?, ?> colitemid;

    @FXML
    private TableColumn<?, ?> colorderid;

    @FXML
    private TableColumn<?, ?> colquantity;

    @FXML
    private TextField discount;

    @FXML
    private TextField itemid;

    @FXML
    private TextField orderid;

    @FXML
    private TextField quantity;

    @FXML
    void deleteOrderDetails(ActionEvent event) {
        orderDetailsController.deleteOrderDetails(orderid.getText());
        tblview.setItems(orderDetailsController.getAllOrderDetails());
    }

    @FXML
    void updateOrderDetails(ActionEvent event) {
        orderDetailsController.updateOrderDetails(Integer.parseInt(quantity.getText()),Integer.parseInt(discount.getText()),orderid.getText());
        tblview.setItems(orderDetailsController.getAllOrderDetails());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colitemid.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        coldiscount.setCellValueFactory(new  PropertyValueFactory<>("discount"));
        tblview.setItems(orderDetailsController.getAllOrderDetails());

        tblview.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) -> {
            if(t1!=null){
                itemid.setText(t1.getItemId());
                orderid.setText(t1.getOrderId());
                quantity.setText(String.valueOf(t1.getQuantity()));
                discount.setText(String.valueOf(t1.getDiscount()));
            }
        }
        ));

    }

}
