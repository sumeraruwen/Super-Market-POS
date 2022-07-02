package lk.ijse.supermarket.controller;

import lk.ijse.supermarket.bo.BOFactory;
import lk.ijse.supermarket.bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.view.tm.CustomerTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddCustomerFormController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCity;
    public JFXTextField txtPostalCode;
    public TableView<CustomerTM> tblCustomers;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnAddNewCustomer;
    public JFXTextField txtCustomerCity;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    //private CustomerDAO customerDAO = new CustomerDAOImpl();
   // CustomerBO customerBO = new CustomerBOImpl();

    public void initialize() {



        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("city"));

        initUI();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCustomerId.setText(newValue.getId());
                txtCustomerName.setText(newValue.getName());
                txtCustomerAddress.setText(newValue.getAddress());

                txtCustomerId.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
                txtCustomerCity.setDisable(false);
            }
        });

        txtCustomerAddress.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        tblCustomers.getItems().clear();
        /*Get all customers*/
        try {

            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
           // ArrayList<CustomerDTO> allCustomers = customerDAO.getAll();

            for(CustomerDTO customer:allCustomers) {
                tblCustomers.getItems().add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress(),customer.getCity()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void initUI() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerId.setDisable(true);
        txtCustomerName.setDisable(true);
        txtCustomerAddress.setDisable(true);
        txtCustomerId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    //Add New Customer
    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCustomerId.setDisable(false);
        txtCustomerName.setDisable(false);
        txtCustomerAddress.setDisable(false);
        txtCustomerId.clear();
        txtCustomerId.setText(generateNewId());
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblCustomers.getSelectionModel().clearSelection();

    }

    //Save Customer
    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String city = txtCustomerCity.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtCustomerName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtCustomerAddress.requestFocus();
            return;
        }else if (!city.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "City should be at least 3 characters long").show();
            txtCustomerAddress.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Customer*/
            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                customerBO.saveCustomers(new CustomerDTO(id,name,address,city));

                tblCustomers.getItems().add(new CustomerTM(id, name, address,city));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            /*Update customer*/
            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }
              //Update Customer

                customerBO.updateCustomers(new CustomerDTO(id,name,address,city));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setCity(city);
            tblCustomers.refresh();
        }

        btnAddNewCustomer.fire();

    }
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

       return customerBO.existCustomers(id);
    }

    //Delete Customer
    public void btnDelete_OnAction(ActionEvent actionEvent) {
        /*Delete Customer*/
        String id = tblCustomers.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }

            customerBO.deleteCustomers(id);

            tblCustomers.getItems().remove(tblCustomers.getSelectionModel().getSelectedItem());
            tblCustomers.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Generate New ID
    private String generateNewId() {
        try {

           return customerBO.generateNewCustomerID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblCustomers.getItems().isEmpty()) {
            return "C00-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }

    }

    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }
}
