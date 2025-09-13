package controller.customercontroller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController implements CustomerServiceController{
    ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

    @Override
    public void addCustomer(Customer customer){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("insert into customer values(?,?,?,?)");
            pst.setObject(1,customer.getCusId());
            pst.setObject(2,customer.getName());
            pst.setObject(3,customer.getAddress());
            pst.setObject(4,customer.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String cusid ){
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement(" delete from customer where cus_id= ?;");
            pst.setObject(1,cusid);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAllCustomerDetails(){
        CustomerList.clear();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement("select * from customer;");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer( resultSet.getString("cus_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"));
                CustomerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return CustomerList;
    }

    @Override
    public void updateCustomer(String cusid,String name,String address, String email) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("update customer set cus_id=?,name=?,address=?,email=? where cus_id=?");
            preparedStatement.setObject(1,cusid);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,email);
            preparedStatement.setObject(5,cusid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
