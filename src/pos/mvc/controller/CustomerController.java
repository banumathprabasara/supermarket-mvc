/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;



import pos.mvc.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import pos.mvc.db.dbConnection;
/**
 *
 * @author banumathprabasara
 */
public class CustomerController {
    public String saveCustomer(CustomerModel customerModel)throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement preparedStatement =  connection.prepareStatement(query);
        preparedStatement.setString(1, customerModel.getCustID());
        preparedStatement.setString(2, customerModel.getTitle());
        preparedStatement.setString(3, customerModel.getName());
        preparedStatement.setString(4, customerModel.getDob());
        preparedStatement.setDouble(5, customerModel.getSalary());
        preparedStatement.setString(6, customerModel.getAddress());
        preparedStatement.setString(7, customerModel.getCity());
        preparedStatement.setString(8, customerModel.getProvince());
        preparedStatement.setString(9, customerModel.getZip());
        
        if (preparedStatement.executeLargeUpdate()>0) {
            
            return "Succes";
        }else{
            return "Fail";
        }
    }
    
    public ArrayList<CustomerModel> getAllCustomers()throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "Select * from Customer";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        ResultSet rst = preparedStatement.executeQuery();
        
        ArrayList<CustomerModel> customerModels = new ArrayList<>();
        
        while (rst.next()) {            
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9));
            
            customerModels.add(cm);
        }
        return customerModels;
    }
    
    public  CustomerModel getCustomer(String custId)throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "Select * from Customer WHERE CustID = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, custId);
        
        ResultSet rst = preparedStatement.executeQuery();
        
        while (rst.next()) {            
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9));
            return cm;
        }
        return null; 
    }
    
    public  String updateCustomer(CustomerModel customerModel) throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "UPDATE Customer SET CustTitle =?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setString(9, customerModel.getCustID());
        preparedStatement.setString(1, customerModel.getTitle());
        preparedStatement.setString(2, customerModel.getName());
        preparedStatement.setString(3, customerModel.getDob());
        preparedStatement.setDouble(4, customerModel.getSalary());
        preparedStatement.setString(5, customerModel.getAddress());
        preparedStatement.setString(6, customerModel.getCity());
        preparedStatement.setString(7, customerModel.getProvince());
        preparedStatement.setString(8 , customerModel.getZip());
        
        if (preparedStatement.executeLargeUpdate()>0) {
            
            return "Succes";
        }else{
            return "Fail";
        }
    }
    
    public String deleteCustomer(String custId) throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "DELETE FROM Customer WHERE CustID=?";
        
        PreparedStatement statement= connection.prepareCall(query);
        
        statement.setString(1, custId);
        if (statement.executeUpdate()>0) {
            
            return "Succes";
        }else{
            return "Fail";
        }
        
        
    }
}