package controller.ordercontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController implements OrderServiceController {
    ObservableList<Order> OrderList = FXCollections.observableArrayList();
    ObservableList<OrderDetails> OrderDetailList = FXCollections.observableArrayList();

    @Override
    public Customer searchCustomer(String cusid) {
        Customer customer = null;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select name,address,email,cus_id from customer where cus_id=?");
            pst.setObject(1, cusid);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("cus_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println("Not Available that CustomerID");
        }
        return customer;
    }
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
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
    ///  //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void addOrder(Order order){
        try {
            Connection conn =DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into orders values (?,?,?,?)");
            pst.setObject(1,order.getOrderId());
            pst.setObject(2,order.getCusId());
            pst.setObject(3,order.getDate());
            pst.setObject(4,order.getTotal());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void addOrderDetails(OrderDetails orderDetails){
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into order_details values (?,?,?,?)");
            pst.setObject(1,orderDetails.getOrderId());
            pst.setObject(2,orderDetails.getItemId());
            pst.setObject(3,orderDetails.getQuantity());
            pst.setObject(4,orderDetails.getDiscount());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////
@Override
    public void  updateQuantity(Integer quantity,String itemId ){
        try {
            Connection conn =DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("Update item set quantity=? where item_id= ? ;");
            pst.setObject(1,searchItem(itemId).getQuantity()-quantity);
            pst.setObject(2,itemId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Override
public ObservableList<Order> getAllOrders(){
        OrderList.clear();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement =  conn.prepareStatement("select * from orders;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order(
                        resultSet.getString("order_id"),
                        resultSet.getString("cus_id"),
                        resultSet.getString("order_date"),
                        resultSet.getInt("total")
                        );
                OrderList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return OrderList;
    }
    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addCustomerDetails(String customerId , String name, String address , String email){
        Customer customer = searchCustomer(customerId);
        if(customer==null){
            try {
                Connection con = DBConnection.getInstance().getConnection();
                PreparedStatement pst = con.prepareStatement("insert into customer values(?,?,?,?);");
                pst.setObject(1,customerId);
                pst.setObject(2,name);
                pst.setObject(3,address);
                pst.setObject(4,email);
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
