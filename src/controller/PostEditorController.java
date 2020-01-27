package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.PaymentBO;
import bussiness.custom.PostWallBO;
import bussiness.custom.UserBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import db.DBConnection;
import dto.PaymentDTO;
import dto.PostWallDTO;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostEditorController implements Initializable {
    public TextArea discriptionPost;
    public Text namePost;
    public JFXRadioButton payment;
    public JFXTextField cardNo;

private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);
private UserBO userBO = BOFactory.getInstance().getBO(BOType.user);
private PostWallBO postWallBO =BOFactory.getInstance().getBO(BOType.postWall);
private PaymentBO paymentBO =BOFactory.getInstance().getBO(BOType.payment);

    public void btn_canclePostOnAction(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void btn_SharePostOnAction(ActionEvent actionEvent) {

            if (discriptionPost.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill tha Post as you want", ButtonType.OK).show();
            } else if (cardNo.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please enter valid card number", ButtonType.OK).show();
            } else if (!payment.isSelected()) {
                new Alert(Alert.AlertType.WARNING, "Please select payment amount", ButtonType.OK).show();
            }else if (!cardNo.getText().matches("[0-9]{15,16}")) {
                new Alert(Alert.AlertType.ERROR, "Please Enter Valid card number").show();
            }else {
                Connection connection = DBConnection.getInstance().getConnection();
                try {
                    connection.setAutoCommit(false);
                    boolean save = postWallBO.save(new PostWallDTO(0, loginBO.getId(), discriptionPost.getText(), Date.valueOf(LocalDate.now())));
                    boolean save1 = paymentBO.save(new PaymentDTO(loginBO.getId(), userDTO.getName(), Long.parseLong(cardNo.getText()), Date.valueOf(LocalDate.now()), 100.00));

                    System.out.println(cardNo.getText());

                    if (save && save1) {
                        connection.commit();
                        discriptionPost.clear();
                        Node source = (Node) actionEvent.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
                    } else {
                        connection.rollback();
                        new Alert(Alert.AlertType.WARNING, "Someting went Wrong please try again", ButtonType.OK).show();
                    }

                }catch (NullPointerException e){
                    System.out.println("numberFORMAT");
                }catch (Exception ex){
                    new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                    Logger.getLogger("").log(Level.SEVERE,null,ex);
                }
            }
        }

    UserDTO userDTO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String id = loginBO.getId();
            userDTO = userBO.find(id);
            namePost.setText(userDTO.getName());

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


    }
}
