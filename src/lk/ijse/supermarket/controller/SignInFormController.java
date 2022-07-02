package lk.ijse.supermarket.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.supermarket.util.Loader;

import java.io.IOException;

public class SignInFormController implements Loader {
    public AnchorPane logInContext;
    public JFXTextField txtName;
    public JFXPasswordField pwdPassword;

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        String tempName = txtName.getText();
        String tempPassword = pwdPassword.getText();
        if (tempName.equals("admin") && tempPassword.equals("1234")) {
            setUi("DashBoardForm");
        }else if (tempName.equals("sumera") && tempPassword.equals("1234")) {
            setUi("CashierDashForm");
        }
    }


    private void setUi(String location) throws IOException {
        Stage stage = (Stage) logInContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }

    @Override
    public void loadUi(String location) throws IOException {
        logInContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        logInContext.getChildren().add(parent);
    }
}
