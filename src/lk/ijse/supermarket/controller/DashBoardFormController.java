package lk.ijse.supermarket.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.util.Loader;

import java.io.IOException;

public class DashBoardFormController implements Loader {

    public AnchorPane dashBoardFormContext;

    public void initialize() throws IOException {
        loadUi("DashBoard");
    }

    public void DashBoardOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DashBoard");
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AddItemForm");
    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("OrderDetailForm");
    }

    @Override
    public void loadUi(String location) throws IOException {
        dashBoardFormContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        dashBoardFormContext.getChildren().add(parent);
    }

    public void btnMostItemsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("MostMovableItemForm");
    }

    public void btnLeastItemsOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("LeastMovableItemForm");

    }

    public void btnSearchOrders(ActionEvent actionEvent) throws IOException {
       loadUi("SearchForm");
    }

    private  void setUi (String URI) throws IOException {
        Parent parent  = FXMLLoader.load(getClass().getResource("../view/"+URI+".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle(URI);
        stage.show();
    }
}
