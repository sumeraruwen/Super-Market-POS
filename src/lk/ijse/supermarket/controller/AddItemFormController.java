package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.custom.ItemBO;
import lk.ijse.supermarket.bo.custom.impl.ItemBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.view.tm.ItemTM;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class AddItemFormController {

    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public TableView<ItemTM> tblItems;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnAddNewItem;
    /*private ItemDAO itemDAO = new ItemDAOImpl();*/
  //  ItemBO itemBO = new ItemBOImpl();
    ItemBO itemBO= (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() {

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        initUI();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "UPDATE PRODUCT" : "SAVE PRODUCT");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");

                txtCode.setDisable(false);
                txtDescription.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
    }

    //Load All Items
    private void loadAllItems() {
        tblItems.getItems().clear();
        try {
            /*Get all items*/

            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            //ArrayList<ItemDTO> allItems = itemDAO.getAll();

           for(ItemDTO item:allItems){
                tblItems.getItems().add(new ItemTM(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }


    //Save Item
    public void btnSave_OnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String description = txtDescription.getText();

        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtDescription.requestFocus();
            return;
        } else if (!txtUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtUnitPrice.requestFocus();
            return;
        } else if (!txtQtyOnHand.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtQtyOnHand.requestFocus();
            return;
        }

        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);


        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }
                //Save Item

                itemBO.saveItem(new ItemDTO(code,description,qtyOnHand,unitPrice));

                tblItems.getItems().add(new ItemTM(code, description, qtyOnHand, unitPrice));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                /*Update Item*/

                itemBO.updateItem(new ItemDTO(code,description,qtyOnHand,unitPrice));

                ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                selectedItem.setDescription(description);
                selectedItem.setQtyOnHand(qtyOnHand);
                selectedItem.setUnitPrice(unitPrice);
                tblItems.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewItem.fire();
    }

    //Delete Item
    public void btnDelete_OnAction(ActionEvent actionEvent) {
        /*Delete Item*/
        String code = tblItems.getSelectionModel().getSelectedItem().getCode();
        try {
            if (!existItem(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
            }

            itemBO.deleteItem(code);

            tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
            tblItems.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Add New Item
    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCode.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtCode.clear();
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblItems.getSelectionModel().clearSelection();
    }
    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
       return itemBO.existItem(code);
    }


    //Generate New ID
    private String generateNewId() {
        try {
            ItemBOImpl itemBO = new ItemBOImpl();
           return itemBO.generateNewItemCode();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";
    }
}
