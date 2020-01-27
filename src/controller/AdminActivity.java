package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.AdminWorkBO;
import bussiness.custom.ComplainBO;
import com.jfoenix.controls.JFXTextField;
import dto.AdminNoteDTO;
import dto.CivilStateDTO;
import dto.ComplainDTO;
import dto.EducationStateDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.AdminNoteTM;
import tm.CivilSatusTM;
import tm.EducationStatusTM;
import tm.ReportTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminActivity {
    public TableView<EducationStatusTM> tbl_EducationState;
    public TableView<CivilSatusTM> tbl_CivilState;
    public TableView<AdminNoteTM> tbl_Notification;

    public JFXTextField txt_educationState;
    public JFXTextField txt_CivilState;
    public JFXTextField txt_AdminNote;

    public AnchorPane root;
    public TableView<ReportTM> tableReport;

    private AdminWorkBO adminWorkBO = BOFactory.getInstance().getBO(BOType.adminWork);
    private ComplainBO complainBO =BOFactory.getInstance().getBO(BOType.complain);

    public void initialize() {
        tbl_EducationState.getItems().clear();
        tbl_CivilState.getItems().clear();
        tbl_Notification.getItems().clear();
        tableReport.getItems().clear();

        tbl_CivilState.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("civilStatus"));
        tbl_EducationState.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("education"));
        tbl_Notification.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("note"));

        tableReport.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("senderId"));
        tableReport.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("blockerId"));

        addItemToTableEdcation();
        addItemToCivilStateTable();
        addItemToAdminNoteTable();
        additemtoreportTable();
    }
    public void btn_educationAdd(ActionEvent actionEvent) {
        try {
            boolean add = adminWorkBO.add(new EducationStateDTO(txt_educationState.getText()));
            if(add){
                txt_educationState.clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong", ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }
    public void btn_educationDelete(ActionEvent actionEvent) {
        EducationStatusTM selectedItem = tbl_EducationState.getSelectionModel().getSelectedItem();
        String education = selectedItem.getEducation();
        try {
            boolean delete = adminWorkBO.delete(education);
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }
    public void addItemToTableEdcation(){
        try {
            List<EducationStateDTO> all = adminWorkBO.findAll();
            ObservableList<EducationStatusTM> items = tbl_EducationState.getItems();

            for (EducationStateDTO data:all) {
                items.add(new EducationStatusTM(data.getEducation()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }
    public void btn_civilStateAdd(ActionEvent actionEvent) {
        try {
            boolean add = adminWorkBO.addC(new CivilStateDTO(txt_CivilState.getText()));
            if(add){
                txt_CivilState.clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }
    public void btn_CivilStateDelete(ActionEvent actionEvent) {
        CivilSatusTM selectedItem = tbl_CivilState.getSelectionModel().getSelectedItem();
        String civil = selectedItem.getCivilStatus();
        try {
            boolean delete = adminWorkBO.deleteC(civil);
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }

    private void addItemToCivilStateTable() {
        try {
            List<CivilStateDTO> all = adminWorkBO.findAllC();
            ObservableList<CivilSatusTM> items = tbl_CivilState.getItems();

            for (CivilStateDTO data:all) {
                items.add(new CivilSatusTM(data.getCivilStatus()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }

    public void btn_NotificationAdd(ActionEvent actionEvent) {
        try {
            boolean add = adminWorkBO.addNOte(new AdminNoteDTO(txt_AdminNote.getText()));
            if(add){
                txt_AdminNote.clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }

    public void btn_NotificationDelete(ActionEvent actionEvent) {
        AdminNoteTM selectedItem = tbl_Notification.getSelectionModel().getSelectedItem();
        String education = selectedItem.getNote();
        try {
            boolean delete = adminWorkBO.deleteNote(education);
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }

    private void addItemToAdminNoteTable() {
        try {
            List<AdminNoteDTO> note = adminWorkBO.findNote();
            ObservableList<AdminNoteTM> items = tbl_Notification.getItems();

            for (AdminNoteDTO data:note) {
                items.add(new AdminNoteTM(data.getNote()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    public void btn_BacktoProfileOnAction(ActionEvent actionEvent) {
        slide("/viwe/profile.fxml");
    }

    public void btn_nextOnaction(ActionEvent actionEvent) {
        slide("/viwe/payment.fxml");
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


    private void additemtoreportTable(){
        try {
            ObservableList<ReportTM> items = tableReport.getItems();
            List<ComplainDTO> all = complainBO.findAll();

            for (ComplainDTO data:
                 all) {

                items.add(new ReportTM(data.getSenderId(),data.getBlockerId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btn_tableReportClear(ActionEvent actionEvent) {
        ReportTM selectedItem = tableReport.getSelectionModel().getSelectedItem();
        String blockerId = selectedItem.getBlockerId();
        try {
            boolean delete = complainBO.Delete(blockerId);
            if(delete){
                System.out.println("delete done");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initialize();
    }

    public void btn_viewProfile(ActionEvent actionEvent) {
        ReportTM selectedItem = tableReport.getSelectionModel().getSelectedItem();
        String blockerId = selectedItem.getBlockerId();

        VisitProfileController.userIdg =blockerId;
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/viwe/profileVisit.fxml"));
        Scene scene= null;
        try {
            scene = new Scene(fxmlLoader.load(),775,605);
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
}
