package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.ComplainBO;
import bussiness.custom.LoginBO;
import bussiness.custom.PhotoWallBO;
import bussiness.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import dto.ComplainDTO;
import dto.FindRollDTO;
import dto.PhotoWallDTO;
import dto.UserDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.NewProfileModel;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VisitProfileController {
    public AnchorPane root;
    public ListView ListViweProfile;
    public ImageView proPic;
    public Text txt_name;
    public Text txt_HomeTown;
    public Text txt_age;
    public Text txt_educationStatus;
    public Text txt_WorkAt;
    public Text txt_Description;
    public Text txt_telephone;

    public static String userIdg;
    public JFXToggleButton makeAdminButton;
    public JFXButton buttonDelete;

    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);
    private ComplainBO complainBO =BOFactory.getInstance().getBO(BOType.complain);
    private UserBO userBO = BOFactory.getInstance().getBO(BOType.user);
    private PhotoWallBO photoWallBO =BOFactory.getInstance().getBO(BOType.photoWall);

    public void initialize() {

        try {
            boolean roll = loginBO.getRoll();

            if (roll) {
                buttonDelete.setVisible(true);
                makeAdminButton.setVisible(true);
            } else {
                makeAdminButton.setVisible(false);
                buttonDelete.setVisible(false);
            }

            FindRollDTO findRollDTO = loginBO.find(userIdg);
            if(findRollDTO.getRoll()){
                makeAdminButton.selectedProperty().setValue(true);
            }
            else {
                makeAdminButton.selectedProperty().setValue(false);
            }

            UserDTO userDTO = userBO.find(userIdg);

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

            proflleWall();

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        makeAdminButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (makeAdminButton.isSelected()) {
                    try {
                        boolean update = loginBO.update(new FindRollDTO(userIdg, true));
                        System.out.println("make admin : "+update);
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                        Logger.getLogger("").log(Level.SEVERE,null,e);
                    }
                } else {
                    try {
                        boolean update = loginBO.update(new FindRollDTO(userIdg, false));
                        System.out.println("make not admin : "+update);
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                        Logger.getLogger("").log(Level.SEVERE,null,e);
                    }
                }
            }
        });
    }

    public void listViwe_profle(MouseEvent mouseEvent) {

    }

    private void proflleWall() {
        try {
            ObservableList items = ListViweProfile.getItems();
            List<PhotoWallDTO> userPhoto = photoWallBO.findUserPhoto(userIdg);
            for (PhotoWallDTO data: userPhoto) {
                NewProfileModel frame = new NewProfileModel();
                frame.setDate(String.valueOf(data.getDate()));
                frame.setShareImage(new Image(data.getImage()));
                items.add(0,frame.getBorderPane1());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }



    public void btn_ReportOnAction(ActionEvent actionEvent) {
        try {
            boolean save = complainBO.save(new ComplainDTO(loginBO.getId(), userIdg));
            if(save){
                new Alert(Alert.AlertType.CONFIRMATION,"Report Done..",ButtonType.OK).show();

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }


    public void btn_DeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "DO YOU WISH TO DELETE YOUR ACCOUNT",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                boolean b = userBO.deleteUser(userIdg);
                if (b) {

                    Stage stage = (Stage)root.getScene().getWindow();
                    stage.close();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }
}
