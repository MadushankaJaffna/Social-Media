package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.RegisterBO;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dto.CivilStateDTO;
import dto.EducationStateDTO;
import dto.LoginDTO;
import dto.UserDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController{


    public TextField working;
    public JFXToggleButton gender;
    public JFXDatePicker birthDay;
    public ComboBox<String>civilState;
    public ComboBox<String> educationState;
    public Button buttonRegister;
    public Button btnBackToLogin;
    public ImageView proPic;
    public AnchorPane root;
    public javafx.scene.text.Text registerNo;
    public JFXTextField name;
    public JFXTextField email;
    public JFXTextField phoneNumber;
    public JFXTextField homeTown;
    public JFXTextField address;
    public JFXTextField aboutMe;
    public JFXTextField confirmPassword;
    public JFXPasswordField password;

    private FileInputStream sendPhoto;

    private LoginBO loginBO =BOFactory.getInstance().getBO(BOType.Login);
    private RegisterBO registerBO = BOFactory.getInstance().getBO(BOType.Register);

    public void initialize() {

        confirmPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!password.getText().equals(confirmPassword.getText())){
                    confirmPassword.setFocusColor(Color.RED);
                }
                else{
                    confirmPassword.setFocusColor(Color.BLUE);
                }
            }
        });
       try {
               String string = registerBO.getLastUid();
               if (string ==null) {
                   registerNo.setText("RE:001");
               } else {

                   String[] split = string.split(":");
                   int s = Integer.parseInt(split[1]);
                   s++;
                   if(s<10){ registerNo.setText("RE:00" + s); }
                   else if(s<100){ registerNo.setText("RE:0" + s);}
                   else if(s<1000){ registerNo.setText("RE:" + s);}
               }
               List<CivilStateDTO> civilStateDTOS = registerBO.civilState();
            List<String> selectedItem =civilState.getItems();
           for(CivilStateDTO civil :civilStateDTOS){
               selectedItem.add(civil.getCivilStatus());
           }

            List<EducationStateDTO> educationStateDTOS = registerBO.educationState();
           List<String> edu =educationState.getItems();
           for(EducationStateDTO educatlevel :educationStateDTOS){
               edu.add(educatlevel.getEducation());
           }
        } catch (Exception e) {
           new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
           Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        gender.setText("Female");
        gender.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(gender.isSelected()==true){
                    gender.setText("Male");
                }
                else{
                    gender.setText("Female");
                }
            }
        });

    }


    public void switchOnAction(ActionEvent actionEvent){
    }

    public void combo_CivilStateOnAction(ActionEvent actionEvent) {

    }

    public void btn_registerOnAction(ActionEvent actionEvent) {
        try {
                if (!name.getText().matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")) {
                    new Alert(Alert.AlertType.INFORMATION, "please Enter valid name").show();
                    name.requestFocus();
                } else if (!email.getText().matches("^[a-z]+(.)?[a-z]+(@)?[a-z]+[.]?[a-z]+$")) {
                    new Alert(Alert.AlertType.INFORMATION, "please Enter valid Email Address").show();
                    email.requestFocus();
                } else if (!phoneNumber.getText().matches("^[+]?[0-9]{3}[-]?[0-9]{7}$")) {
                    new Alert(Alert.AlertType.INFORMATION, "please Enter valid Phone number").show();
                    phoneNumber.requestFocus();
                } else if (!address.getText().matches("\\S+[a-zA-Z0-9/.,:\\sa-zA-Z0-9]+\\S+")) {
                    new Alert(Alert.AlertType.INFORMATION, "please Enter valid address").show();
                    address.requestFocus();
                } else if (!password.getText().matches("^[0-9A-Za-z!@#$%^&*]{6,}$")) {
                    new Alert(Alert.AlertType.INFORMATION, "please Enter strong password using at least 6 or more characters").show();
                    address.requestFocus();
                }
                else if(civilState.getSelectionModel().isEmpty()||educationState.getSelectionModel().isEmpty()||homeTown.getText().isEmpty()||
                working.getText().isEmpty()||aboutMe.getText().isEmpty()||birthDay.getValue().toString().isEmpty()){
                    new Alert(Alert.AlertType.INFORMATION, "please Fill all the Fields").show();
                }

            else {
                boolean b = registerBO.saveUser(new UserDTO(registerNo.getText(), name.getText(), gender.getText(), email.getText(), Integer.parseInt(phoneNumber.getText()),
                        civilState.getSelectionModel().getSelectedItem(),
                        aboutMe.getText(), sendPhoto,
                        address.getText(), homeTown.getText(), Date.valueOf(birthDay.getValue()),
                        working.getText(), educationState.getSelectionModel().getSelectedItem()));

                boolean save = loginBO.save(new LoginDTO(registerNo.getText(), email.getText(), password.getText()));

                if (b & save) {
                    slide();
                }
            }

            }catch(Exception e){
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    public void btn_backToLoginOnAction(ActionEvent actionEvent) {
        slide();

    }
    private void slide(){
        URL resource = this.getClass().getResource("/viwe/loginForm.fxml");
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


    public void proPicOnClickAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg","*.JPG", "*.gif"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if (file != null) {
            Image image=new Image(file.toURI().toString(),1000,1000,true,true);
           // proPic.setPreserveRatio(true);
            proPic.setImage(image);
            try {
                sendPhoto = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }

    public void combo_educationStateOnAction(ActionEvent actionEvent) {
    }

}
