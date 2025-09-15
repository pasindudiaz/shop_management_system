package controller.orderdetailscontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
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

}
