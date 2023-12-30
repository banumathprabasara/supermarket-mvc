/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pos.mvc.db.dbConnection;
import pos.mvc.model.ItemModel;

/**
 *
 * @author banumathprabasara
 */
public class ItemController {
    
    public ArrayList<ItemModel> getAllItem() throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "Select * from Item";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rst = preparedStatement.executeQuery();
        
        ArrayList<ItemModel> itemModels = new ArrayList<>();
        
        while (rst.next()) {            
            ItemModel im = new ItemModel(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3), 
                    rst.getDouble(4),
                    rst.getInt(5));
                itemModels.add(im);
        }
        return itemModels;
    }
    
    public String saveItem(ItemModel itemModel) throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        
        PreparedStatement preparedStatement =  connection.prepareStatement(query);
        
        preparedStatement.setString(1, itemModel.getItemCode());
        preparedStatement.setString(2, itemModel.getDescription());
        preparedStatement.setString(3, itemModel.getPackSize());
        preparedStatement.setDouble(4, itemModel.getUnitPrize());
        preparedStatement.setInt(5, itemModel.getQoh());  
        
        if (preparedStatement.executeLargeUpdate()>0) {
            
            return "Succes";
        }else{
            return "Fail";
        }
    }
    
    public ItemModel searchItem(String itemCode) throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "Select * from Item WHERE ItemCode = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, itemCode);
        
        ResultSet rst = preparedStatement.executeQuery();
        
        while (rst.next()) {            
            ItemModel im = new ItemModel(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5));
            
            return im;
        }
        return null; 
    }
    
    public String updateItem(ItemModel itemModel) throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setString(5, itemModel.getItemCode());
        preparedStatement.setString(1, itemModel.getDescription());
        preparedStatement.setString(2, itemModel.getPackSize());
        preparedStatement.setDouble(3, itemModel.getUnitPrize());
        preparedStatement.setInt(4, itemModel.getQoh());
        
        if (preparedStatement.executeLargeUpdate()>0) {
            
            return "Succes";
        }else{
            return "Fail";
        }
    }
    
    public String deleteItem(String itemCode) throws SQLException{
        Connection connection = dbConnection.getInstance().geConnection();
        
        String query = "DELETE FROM Item WHERE ItemCode=?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setString(1, itemCode);
        if (preparedStatement.executeLargeUpdate()>0) {
            
            return "Succes";
        }else{
            return "Fail";
        }
        
    }
}
