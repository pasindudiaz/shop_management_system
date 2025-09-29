package controller.itemcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    ItemServiceController itemServiceController = new ItemController();

    public TextField itemid;
    public TextField name;
    public TextField description;
    public TextField unitprice;
    public TextField quantity;

    public TableView <Item>tblview;
    public TableColumn colitemid;
    public TableColumn colitemname;
    public TableColumn colitemdescription;
    public TableColumn colunitprice;
    public TableColumn colquantity;

    public void addItem(ActionEvent actionEvent) {
        itemServiceController.addItem(new Item(itemid.getText(), name.getText(), description.getText(), Integer.parseInt(unitprice.getText()), Integer.parseInt(quantity.getText())));
        tblview.setItems(itemServiceController.getALLItemDetails());
    }

    public void updateItem(ActionEvent actionEvent) {
        itemServiceController.updateItem(itemid.getText(), name.getText(), description.getText(), unitprice.getText(), quantity.getText());
        tblview.setItems(itemServiceController.getALLItemDetails());
    }

    public void deleteItem(ActionEvent actionEvent) {
        itemServiceController.deleteItem(itemid.getText());
        clearItem(null);
        tblview.setItems(itemServiceController.getALLItemDetails());
    }

    public void clearItem(ActionEvent actionEvent) {
        itemid.setText(null);
        name.setText(null);
        description.setText(null);
        unitprice.setText(null);
        quantity.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colitemid.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colitemname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colitemdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblview.setItems(itemServiceController.getALLItemDetails());

        tblview.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, t1) ->{
            if(t1!=null){
                itemid.setText(t1.getItemId());
                name.setText(t1.getName());
                description.setText(t1.getDescription());
                unitprice.setText(String.valueOf(t1.getUnitPrice()));
                quantity.setText(String.valueOf(t1.getQuantity()));
            }
        }
        ));
    }
}
