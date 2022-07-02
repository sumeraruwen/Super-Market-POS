package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.custom.CustomerBO;
import lk.ijse.supermarket.bo.custom.OrderDetailBO;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import lk.ijse.supermarket.util.CrudUtil;
import lk.ijse.supermarket.view.tm.CustomerTM;
import lk.ijse.supermarket.view.tm.OrderDetailTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDetailFormController {
    public TableView<OrderDetailDTO> tblOrderDetails;
    public TableColumn colId;
    public TableColumn colCode;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colTotal;
    public JFXTextField txtSearchBar;
    private Object OrderDetailDTO;
    private Object Predicate;

    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAIL);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory("oid"));
        colCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));


        try {
            viewOrderDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

       /* try {
            searchFilter();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
       // searchFilter();

    }


    private void viewOrderDetails() throws SQLException, ClassNotFoundException {
       /* ResultSet result = CrudUtil.execute("SELECT * FROM OrderDetails");

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
        tblOrderDetails.setItems(obList2);*/

        try {
            /*Get all items*/
           /* Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM OrderDetails");


            while (rst.next()) {
                tblOrderDetails.getItems().add(new OrderDetailDTO(rst.getString("oid"),rst.getString("itemCode"),rst.getInt("qty"),rst.getBigDecimal("unitPrice"),rst.getBigDecimal("total")));
            }*/

            //new code 3 june
            //ArrayList<ItemDTO> allItems = itemDAO.getAll();

            ArrayList<OrderDetailDTO> allOrderDetail = orderDetailBO.getAllOrderDetail();
            // ArrayList<CustomerDTO> allCustomers = customerDAO.getAll();

            for(OrderDetailDTO orders:allOrderDetail) {
                tblOrderDetails.getItems().add(new OrderDetailDTO(orders.getOid(),orders.getItemCode(),orders.getQty(),orders.getUnitPrice(),orders.getTotal()));
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

   /* private void searchFilter() {

      FilteredList<OrderDetailDTO> filterData = new FilteredList<>(OrderDetailDTO, e->true);
      txtSearchBar.setOnKeyReleased(e->{

          txtSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
          filterData.setPredicate(Predicate<? super OrderDetailDTO>) obList2->{

              if(newValue==null){
                  return true;
              }
              String toLowerCaseFilter= newValue.toLowerCase();
              if(obList2.getOid().){

              }

              });

          });
      });
    }*/
}
