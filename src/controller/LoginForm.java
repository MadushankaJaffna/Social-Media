package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import cript.LikerCript;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginForm{
    public JFXTextField userNameOrEmail;
    public JFXPasswordField password;
    public AnchorPane root;
    public JFXProgressBar progressBar;

    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);


    public void initialize() {
        progressBar.setVisible(false);
    }

    public void btn_signUpOnAction(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/viwe/registerForm.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong", ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }

    public void btn_logInOnAction(ActionEvent actionEvent) {
        if(userNameOrEmail.getText().isEmpty() || password.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"Please fill all information to login").show();
        }
        else {
            if (!userNameOrEmail.getText().matches("^[a-z]+(.)?[a-z]+(@)?[a-z]+[.]?[a-z]+$")) {
                        new Alert(Alert.AlertType.INFORMATION, "please enter valid mail to login").show();
                        userNameOrEmail.requestFocus();
                    } else {
                                String user = userNameOrEmail.getText();
                                String text = password.getText();
                                try {
                                    boolean compare = loginBO.compare(user, text);

                                    if (compare) {
                                        URL resource = this.getClass().getResource("/viwe/home.fxml");
                                        Parent root = null;
                                        try {
                                            root = FXMLLoader.load(resource);
                                        } catch (IOException e) {
                                            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                                            Logger.getLogger("").log(Level.SEVERE,null,e);
                                        }
                                        Scene scene = new Scene(root);
                                        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
                                        primaryStage.setScene(scene);
                                        primaryStage.centerOnScreen();

                                    }

                                } catch (Exception e) {
                                    new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                                    Logger.getLogger("").log(Level.SEVERE,null,e);
                                }

                    }

        }

    }


}
