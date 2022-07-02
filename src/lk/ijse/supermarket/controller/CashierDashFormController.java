package lk.ijse.supermarket.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.util.Loader;

import java.io.IOException;

public class CashierDashFormController implements Loader {
    public AnchorPane cashierDashContext;
    public AnchorPane cashierDashFormContext;

    public void initialize() throws IOException {
        loadUi("PlaceOrderForm");

    }


    public void btnOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("OrderDetailForm");

    }

    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignInForm");
    }

    @Override
    public void loadUi(String location) throws IOException {
        cashierDashFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        cashierDashFormContext.getChildren().add(parent);
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) cashierDashContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("PlaceOrderForm");
    }

    public void btnViewSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddCustomerForm");
    }
}
