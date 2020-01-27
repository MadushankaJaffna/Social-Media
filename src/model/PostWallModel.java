package model;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.NotificationBO;

import controller.HomeController;
import controller.VisitProfileController;
import dto.NotificationDTO;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;


public class PostWallModel {
    private AnchorPane mother;
    private ImageView proPic;
    private ImageView hart;
    private javafx.scene.text.Text name;
    private javafx.scene.text.Text date;
    private javafx.scene.text.Text userId;
    private javafx.scene.text.Text description;
    private String PostId;


    private NotificationBO notificationBO = BOFactory.getInstance().getBO(BOType.notification);
    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);

    public static String ViweUserId;

    public PostWallModel() {


        this.mother= new AnchorPane();

        mother.setPrefSize(600,300);
        mother.setStyle("-fx-border-radius: 10");
        mother.setStyle("-fx-border-color: aqua");
        mother.setStyle("-fx-background-color: white");

        this.proPic =new ImageView();
        Circle clip = new Circle(50,59,40.0);
        proPic.setClip(clip);
        //proPic.setPreserveRatio(true);
        proPic.setFitHeight(90);proPic.setFitWidth(90);proPic.setX(3);proPic.setY(9);proPic.setPickOnBounds(true);
        proPic.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                VisitProfileController.userIdg = userId.getText();

                //ViweUserId = userId.getText();
               FXMLLoader fxmlLoader= new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/viwe/profileVisit.fxml"));
                Scene scene= null;
                try {
                    scene = new Scene(fxmlLoader.load(),775,605);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
               stage.setScene(scene);
               stage.show();
            }
        });

        this.hart = new ImageView();

        hart.setFitHeight(40);hart.setFitWidth(40);hart.setX(10);hart.setY(260);hart.setPickOnBounds(true);
        hart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    hart.setImage(new Image(new FileInputStream("/home/madushanka/Desktop/Cupid/src/image/heart red.png")));
                    if(!loginBO.getId().equals(userId.getText())) {
                        boolean save = notificationBO.save(new NotificationDTO(loginBO.getId(), userId.getText(), PostId));


                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    try {
                        notificationBO.delete(PostId);
                        hart.setImage(new Image(new FileInputStream("/home/madushanka/Desktop/Cupid/src/image/heart-white.png")));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });

        this.name = new Text();

        name.setX(189);name.setY(80);name.setStrokeType(StrokeType.OUTSIDE);name.setStrokeWidth(0.0);name.setWrappingWidth(170);name.setScaleX(2);name.setScaleY(2);

        this.description = new Text();
        description.setX(230);description.setY(140);description.setStrokeType(StrokeType.OUTSIDE);
        description.setStrokeWidth(0.0);description.setWrappingWidth(348);description.setScaleX(2);description.setScaleY(2);

        this.date = new Text();
        date.setX(105);date.setY(100);date.setStrokeType(StrokeType.OUTSIDE);date.setStrokeWidth(0.0);date.setWrappingWidth(170);

        mother.getChildren().add(proPic);
        mother.getChildren().add(date);
        mother.getChildren().add(name);
        mother.getChildren().add(description);
        mother.getChildren().add(hart);

        this.userId = new Text();

    }

    public PostWallModel(AnchorPane mother, ImageView proPic, ImageView hart, Text name, Text date, Text description) {
        this.mother = mother;
        this.proPic = proPic;
        this.hart = hart;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public AnchorPane getMother() {
        return mother;
    }

    public void setMother(AnchorPane mother) {
        this.mother = mother;
    }

    public ImageView getProPic() {
        return proPic;
    }

    public void setProPic(Image Pic) {
        proPic.setImage(Pic);
    }

    public ImageView getHart() {
        return hart;
    }

    public void setHart(String hart) throws FileNotFoundException {
        this.hart.setImage(new Image(new FileInputStream(hart)));
    }

    public Text getName() {
        return name;
    }

    public void setName(String datname) {
        name.setText(datname);
    }
    public void setUserID(String txtId){
        userId.setText(txtId);
    }

    public Text getDate() {
        return date;
    }

    public void setDate(String dat) {
        date.setText(dat);
    }

    public Text getDescription() {
        return description;
    }

    public void setDescription(String des) {
        description.setText(des);
    }

    public void setPostId(String postId){
        this.PostId = postId;
    }
}