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
    public void updateOrderDetails(Integer newquantity, Integer discount, String orderid, String itemid) {
        Integer oldQuantity = searchOrderDetails(orderid).getQuantity();
        if (oldQuantity < newquantity) {
            Integer higherValue = newquantity - oldQuantity;
            updateHigherItemQuantity(higherValue, itemid);
        } else {
            Integer lowValue = oldQuantity - newquantity;
            updateLowerItemQuantity(lowValue, itemid);
        }
        updateOrderTotal(orderid, itemid, newquantity, discount);
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update order_details set quantity = ? , discount = ? where order_id=?;");
            pst.setObject(1, newquantity);
            pst.setObject(2, discount);
            pst.setObject(3, orderid);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public OrderDetails searchOrderDetails(String orderId) {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from order_details where order_id =?;");
            pst.setObject(1, orderId);
            ResultSet resultset = pst.executeQuery();
            OrderDetails orderDetails = null;
            while (resultset.next()) {
                orderDetails = new OrderDetails(
                        resultset.getString("order_id"),
                        resultset.getString("item_id"),
                        resultset.getInt("quantity"),
                        resultset.getInt("discount")
                );
            }
            return orderDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateHigherItemQuantity(Integer changeValue, String itemId) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update item set quantity = ? where item_id = ? ;");
            pst.setObject(1, getItemQuantity(itemId).getQuantity() - changeValue);
            pst.setObject(2, itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLowerItemQuantity(Integer changeValue, String itemId) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update item set quantity = ? where item_id = ? ;");
            pst.setObject(1, getItemQuantity(itemId).getQuantity() + changeValue);
            pst.setObject(2, itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item getItemQuantity(String itemId) {
        Item item = null;
        try {
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement pst = con.prepareStatement("select * from item where item_id = ?;");
            pst.setObject(1, itemId);
            ResultSet resultSet = pst.executeQuery();
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

    @Override
    public void updateOrderTotal(String orderId, String itemId, Integer quantity, Integer discount) {
        Integer beforeDiscountTotal = getItemQuantity(itemId).getUnitPrice() * quantity;
        Integer afterDiscountTotal = (int) (beforeDiscountTotal - (beforeDiscountTotal * (discount / 100.0)));
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("update orders set total=? where order_id = ?;");
            pst.setObject(1, afterDiscountTotal);
            pst.setObject(2, orderId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
