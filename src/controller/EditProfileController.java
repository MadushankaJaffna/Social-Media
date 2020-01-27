package controller;


import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.RegisterBO;
import bussiness.custom.UserBO;
import com.jfoenix.controls.*;
import dto.CivilStateDTO;
import dto.EducationStateDTO;
import dto.UserDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditProfileController{

    public JFXToggleButton gender;
    public ComboBox civilState;
    public JFXDatePicker birthDay;
    public ComboBox educationState;
    public ImageView proPic;
    public JFXButton btnBackToProfile;
    public JFXButton buttonSave;
    public JFXTextField name;
    public JFXTextField email;
    public JFXTextField phoneNumber;
    public JFXTextField homeTown;
    public JFXTextField working;
    public JFXTextField address;
    public JFXTextField aboutMe;
    public JFXTextField confirmPassword;
    public JFXPasswordField password;
    public Text registerNo;
    public AnchorPane root;
    private FileInputStream sendPhoto;


    private UserBO userBO = BOFactory.getInstance().getBO(BOType.user);
    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);
    private RegisterBO registerBO = BOFactory.getInstance().getBO(BOType.Register);

    public void initialize() {

        try {

            UserDTO userDTO = userBO.find(loginBO.getId());

            name.setText(userDTO.getName());
            email.setText(userDTO.getEmail());
            phoneNumber.setText(String.valueOf(userDTO.getPhoneNo()));
            homeTown.setText(userDTO.getHomeTown());
            address.setText(userDTO.getAddress());
            aboutMe.setText(userDTO.getDescription());
            working.setText(userDTO.getWorking());
            registerNo.setText(userDTO.getUserID());

            birthDay.setValue(userDTO.getBirthDay().toLocalDate());

            try {
                proPic.setImage(new Image(userDTO.getProPic()));
            }catch (NullPointerException e){
                proPic.setImage(new Image(new FileInputStream("src/image/avatar.png")));
            }
           // sendPhoto= (FileInputStream) userDTO.getProPic();

            if(userDTO.getGender().equals("Male")){
                gender.selectedProperty().setValue(true);
                gender.setText("Male");
            }
            else{
                gender.selectedProperty().setValue(false);
                gender.setText("female");
            }

            List<CivilStateDTO> civilStateDTOS = registerBO.civilState();
            List<String> selectedItem = civilState.getItems();
            for(CivilStateDTO civil :civilStateDTOS){
                selectedItem.add(civil.getCivilStatus());
            }
            civilState.getSelectionModel().select(userDTO.getCivilStatus());

            List<EducationStateDTO> educationStateDTOS = registerBO.educationState();
            List<String> edu =educationState.getItems();
            for(EducationStateDTO educatlevel :educationStateDTOS){
                edu.add(educatlevel.getEducation());
            }
            educationState.getSelectionModel().select(userDTO.getEducation());


        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong", ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

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

    public void switchOnAction(ActionEvent actionEvent) {
    }

    public void combo_CivilStateOnAction(ActionEvent actionEvent) {
    }

    public void combo_educationStateOnAction(ActionEvent actionEvent) {
    }

    public void proPicOnClickAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg","*.JPG", "*.gif"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if (file != null) {
            Image image=new Image(file.toURI().toString(),1000,1000,true,true);
            //proPic.setPreserveRatio(true);
            proPic.setImage(image);
            try {
                sendPhoto = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }

    public void btn_saveOnAction(ActionEvent actionEvent) throws Exception {
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
        }
        else if(civilState.getSelectionModel().isEmpty()||educationState.getSelectionModel().isEmpty()||homeTown.getText().isEmpty()||
                working.getText().isEmpty()||aboutMe.getText().isEmpty()||birthDay.getValue().toString().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "please Fill all the Fields").show();
        }
        else{
        boolean update = userBO.update(new UserDTO(
                registerNo.getText(), name.getText(), gender.getText(),
                email.getText(), Integer.parseInt(phoneNumber.getText()),
                civilState.getSelectionModel().getSelectedItem().toString(),
                aboutMe.getText(),sendPhoto,
                address.getText(), homeTown.getText(), Date.valueOf(birthDay.getValue()),
                working.getText(),
                educationState.getSelectionModel().getSelectedItem().toString()));
        if(update){
            new Alert(Alert.AlertType.INFORMATION, "Save Done..!").show();
        }
        }
    }

    public void btn_backToProfileOnAction(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/viwe/profile.fxml");
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


}
