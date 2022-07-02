package lk.ijse.supermarket.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.custom.OrderDetailBO;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import lk.ijse.supermarket.util.CrudUtil;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class MostMovableItemFormController {
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAIL);


    public TableView<OrderDetailDTO> tblMovableItems;
    public TableColumn colCode;
    public TableColumn colQty;


    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));

        try {
            mostMovableItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void mostMovableItems() throws SQLException, ClassNotFoundException {
       /* ResultSet result = CrudUtil.execute("SELECT * FROM OrderDetails ORDER BY qty DESC LIMIT 3 ");
       // ResultSet result = CrudUtil.execute("SELECT DISTINCT itemCode FROM OrderDetails ORDER BY qty DESC LIMIT 3 ");


        ObservableList<OrderDetailDTO> obList2 = FXCollections.observableArrayList();

        while (result.next()) {

            obList2.add(
                    new OrderDetailDTO(
                            result.getString(1),
                            result.getString(2),
                            result.getInt(3),
                            result.getBigDecimal(4),
                            result.getBigDecimal(5)

                    )
            );
        }
        tblMovableItems.setItems(obList2);*/
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM OrderDetails ORDER BY qty DESC LIMIT 3");


        while (rst.next()) {
            tblMovableItems.getItems().add(new OrderDetailDTO(rst.getString("oid"),rst.getString("itemCode"),rst.getInt("qty"),rst.getBigDecimal("unitPrice"),rst.getBigDecimal("total")));
        }



    }

}
