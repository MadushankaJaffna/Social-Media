package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.PaymentBO;
import db.DBConnection;
import dto.PaymentDTO;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentController{
    public AnchorPane root;

    public TableView<PaymentTM> tbl_payment;

    private PaymentBO paymentBO = BOFactory.getInstance().getBO(BOType.payment);

    public void initialize() {
        tbl_payment.getItems().clear();

        tbl_payment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tbl_payment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_payment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cardno"));
        tbl_payment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_payment.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            List<PaymentDTO> all = paymentBO.findAll();
            ObservableList<PaymentTM> items = tbl_payment.getItems();

            for (PaymentDTO data:
                 all) {
                items.add(new PaymentTM(data.getUserId(),data.getName(),
                        data.getCardno(),data.getDate(), data.getPrice()));
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong", ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }


    public void btn_BackOnAction(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/viwe/adminActivity.fxml");
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

    public void btn_printOnAction(ActionEvent actionEvent) {
        JasperDesign load = null;
        try {
            load = JRXmlLoader.load(PaymentController.class.getResourceAsStream("/reports/Liker.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            Map<String,Object> params = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }


    public void btn_ClearOnAction(ActionEvent actionEvent) {
        try {
            boolean clear = paymentBO.clear();
            if(clear){
                System.out.println("Payment Clear Success");
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        initialize();
    }

    public void btn_RestorAndBacpData(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/viwe/restoreAndBackup.fxml");
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
