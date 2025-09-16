package controller.orderdetailscontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsController implements OrderDetailsserviceController {
    ObservableList<OrderDetails> OrderDetailsList= FXCollections.observableArrayList();

    @Override
    public ObservableList<OrderDetails> getAllOrderDetails(){
        OrderDetailsList.clear();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from  order_details ; ");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                OrderDetails orderDetails = new OrderDetails( resultSet.getString("order_id"),
                        resultSet.getString("item_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("discount"));
                OrderDetailsList.add(orderDetails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return OrderDetailsList;
    }

    @Override
    public  void deleteOrderDetails(String orderid){
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("delete from orders where order_id = ?;");
            pst.setObject(1,orderid);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrderDetails(Integer quantity, Integer discount,String orderid){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update order_details set quantity = ? , discount = ? where order_id=?;");
            pst.setObject(1,quantity);
            pst.setObject(2,discount);
            pst.setObject(3,orderid);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateQuantity(Integer quantity, String itemId) {
        searchItem(itemId);


    }

    public Item searchItem(String itemId) {
        Item item = null;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select item_id,name,description,unit_price,quantity from item where item_id=?");
            preparedStatement.setObject(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = new Item(
                        resultSet.getString("item_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("unit_price"),
                        resultSet.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;

    }

}
