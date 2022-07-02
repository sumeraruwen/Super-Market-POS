package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXTextField;
import lk.ijse.supermarket.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchFormController {
    public JFXTextField txtId;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtTotal;


    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade","root","1234");
        String sql= "SELECT * FROM OrderDetails WHERE oid='"+txtId.getText()+"'";
        PreparedStatement stm= DBConnection.getDbConnection().getConnection().prepareStatement(sql);
        ResultSet result = stm.executeQuery(sql);
        if (result.next()){
            txtCode.setText(result.getString(2));
            txtQty.setText(result.getString(3));
            txtUnitPrice.setText(result.getString(4));
            txtTotal.setText(result.getString(5));
           // txtQty.setText(String.valueOf(result.getString(4)));
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }


    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
