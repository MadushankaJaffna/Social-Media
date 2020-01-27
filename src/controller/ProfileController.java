package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.PhotoWallBO;
import bussiness.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import dto.PhotoWallDTO;
import dto.UserDTO;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.NewProfileModel;

import java.io.IOException;

import java.net.URL;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfileController{

    public AnchorPane root;
    public ListView ListViweProfile;
    public ImageView proPic;
    public Text txt_name;
    public Text txt_HomeTown;
    public Text txt_age;
    public Text txt_educationStatus;
    public Text txt_Description;
    public Text txt_telephone;
    public Text txt_WorkAt;
    public Button btnAdmin;
    public JFXButton btnDelete;

    private UserBO userBO = BOFactory.getInstance().getBO(BOType.user);
    private LoginBO loginBO =BOFactory.getInstance().getBO(BOType.Login);
    private PhotoWallBO photoWallBO =BOFactory.getInstance().getBO(BOType.photoWall);

    public void initialize() {
        try {
            boolean roll = loginBO.getRoll();
            if(roll) {
                btnAdmin.setVisible(true);
            }
            else{
                btnAdmin.setVisible(false);
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }



        try {

            proflleWall();

            UserDTO userDTO = userBO.find(loginBO.getId());

            try {
                proPic.setImage(new Image(userDTO.getProPic()));
            }catch (NullPointerException e){

            }
            Period age = Period.between(userDTO.getBirthDay().toLocalDate(), LocalDate.now());

            txt_name.setText(userDTO.getName());
            txt_HomeTown.setText(userDTO.getHomeTown());
            txt_educationStatus.setText(userDTO.getEducation());
            txt_telephone.setText(String.valueOf(userDTO.getPhoneNo()));
            txt_WorkAt.setText(userDTO.getWorking());
            txt_Description.setText(userDTO.getDescription());
            txt_age.setText(String.valueOf(age.getYears()));

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    private void proflleWall() {

        try {
            ObservableList items = ListViweProfile.getItems();
            List<PhotoWallDTO> userPhoto = photoWallBO.findUserPhoto(loginBO.getId());
            UserDTO userDTO = userBO.find(loginBO.getId());
            for (PhotoWallDTO data: userPhoto) {
                NewProfileModel frame = new NewProfileModel();

                frame.setShareImage(new Image(data.getImage()));
                frame.setPostId(data.getUserId());

            items.add(0,frame.getBorderPane1());

            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }

    public void btn_homeOnAction(ActionEvent actionEvent) {
        slide("/viwe/home.fxml");
    }

    public void listViwe_profle(MouseEvent mouseEvent) { }

    public void btn_AdminOnAction(ActionEvent actionEvent) {
        slide("/viwe/adminActivity.fxml");
    }

    public void btn_EditOnAction(ActionEvent actionEvent) {
        slide("/viwe/editProfile.fxml");
    }

    public void btn_DeleteOnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "DO YOU WISH TO DELETE YOUR ACCOUNT",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                boolean b = userBO.deleteUser(loginBO.getId());
                if (b) {
                    slide("/viwe/loginForm.fxml");
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }


    public void slide(String parth){
        URL resource = this.getClass().getResource(parth);
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


    public void btn_deletePostOnAction(ActionEvent actionEvent) {
        String deletePostIdText = NewProfileModel.deletePostIdText;
        Alert alert = new Alert(Alert.AlertType.WARNING, "DO YOU WISH TO DELETE THIS PHOTO",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                boolean delete = photoWallBO.delete(deletePostIdText);
                if(delete){
                    ListViweProfile.getItems().clear();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }
}
