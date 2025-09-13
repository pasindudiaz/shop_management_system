package controller.itemcontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import model.Item;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemServiceController {
    ObservableList<Item> ItemList = FXCollections.observableArrayList();
    @Override
    public void addItem(Item item){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst =  conn.prepareStatement("insert into item values(?,?,?,?,?);");
            pst.setObject(1,item.getItemId());
            pst.setObject(2,item.getName());
            pst.setObject(3,item.getDescription());
            pst.setObject(4,item.getUnitPrice());
            pst.setObject(5,item.getQuantity());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteItem(String itemId){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("delete from item where item_id = ? ;");
            pst.setObject(1,itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Item> getALLItemDetails(){
        ItemList.clear();
        try {
            Connection conn =DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from item;");
            ResultSet resultSet=  pst.executeQuery();
            while (resultSet.next()){
                Item item = new Item(resultSet.getString("item_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("unit_price"),
                        resultSet.getInt("quantity"));
                ItemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ItemList;
    }

    @Override
    public void updateItem(String itemid, String name, String description, String quantity, String unitprice) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("update item set item_id=?,name=?,description=?,unit_price=?,quantity=? where item_id=?");
            preparedStatement.setObject(1,itemid);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,description);
            preparedStatement.setObject(4,quantity);
            preparedStatement.setObject(5,unitprice);
            preparedStatement.setObject(6,itemid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
