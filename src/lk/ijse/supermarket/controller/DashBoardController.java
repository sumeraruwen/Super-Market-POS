package lk.ijse.supermarket.controller;

import javafx.scene.chart.*;
import lk.ijse.supermarket.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardController {

    public AnchorPane dashBoardContext;
    public AreaChart areaChart;
    public Label lblTotalOrders;
    public Label lblTotalItems;
    public Label lblTotalSales;
    public BarChart <String, Number> barchart;
    public CategoryAxis caId;
    public NumberAxis naSales;

    public void initialize(){
        try {
            orders();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            items();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            sales();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        //series.setName("Order Summary");
        series.getData().add(new XYChart.Data<>("P001", 6000));
        series.getData().add(new XYChart.Data<>("P002", 5000));
        series.getData().add(new XYChart.Data<>("P003", 4000));
        series.getData().add(new XYChart.Data<>("P004", 7000));

        barchart.getData().add(series);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        //series.setName("Order Summary");

        series2.getData().add(new XYChart.Data<>("P001", 1000));
        series2.getData().add(new XYChart.Data<>("P002", 3000));
        series2.getData().add(new XYChart.Data<>("P003", 2000));
        series2.getData().add(new XYChart.Data<>("P004", 4000));
        series2.getData().add(new XYChart.Data<>("P005", 3000));


        areaChart.getData().add(series2);


    }






    private void orders() throws SQLException, ClassNotFoundException {
        ResultSet result = DBConnection.getDbConnection().
                getConnection().prepareStatement("SELECT COUNT(oid) FROM Orders ").executeQuery();
        if (result.next()) {
            int customerCount = result.getInt(1);
            lblTotalOrders.setText(String.valueOf(customerCount));
        }
    }

    private void items() throws SQLException, ClassNotFoundException {
        ResultSet result = DBConnection.getDbConnection().
                getConnection().prepareStatement("SELECT SUM(qty) FROM OrderDetails").executeQuery();
        if (result.next()) {
            int customerCount = result.getInt(1);
            lblTotalItems.setText(String.valueOf(customerCount));
        }
    }

    private void sales() throws SQLException, ClassNotFoundException {
        ResultSet result = DBConnection.getDbConnection().
                getConnection().prepareStatement("SELECT SUM(total) FROM OrderDetails").executeQuery();
        if (result.next()) {
            int customerCount = result.getInt(1);
            lblTotalSales.setText(String.valueOf(customerCount));
        }
    }


    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignInForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }


}
