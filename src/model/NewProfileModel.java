package model;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.NotificationBO;

import dto.NotificationDTO;
import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

public class NewProfileModel {
    private BorderPane borderPane1;

    private ImageView shareImage;
    private ImageView proPic;
    private ImageView hartReaction;
    private Text name;
    private Text date;
    private Text postId;
    private String UserId;

    public static String deletePostIdText;

    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);
    private NotificationBO notificationBO = BOFactory.getInstance().getBO(BOType.notification);

    public NewProfileModel() {

        postId=new Text();
        borderPane1=new BorderPane();
        borderPane1.prefHeight(500);borderPane1.prefWidth(400);borderPane1.setLayoutX(1);
        borderPane1.setLayoutY(600);

        borderPane1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    deletePostIdText = postId.getText();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        shareImage=new ImageView();
        shareImage.setPreserveRatio(true);
        shareImage.setFitHeight(350);shareImage.setFitWidth(480);
        shareImage.setX(0);shareImage.setY(0);shareImage.setPickOnBounds(true);

        hartReaction = new ImageView();
        hartReaction.setFitHeight(30);hartReaction.setFitWidth(30);hartReaction.setX(8);hartReaction.setY(8);hartReaction.setPickOnBounds(true);
        hartReaction.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    hartReaction.setImage(new Image(new FileInputStream("/home/madushanka/Desktop/Cupid/src/image/heart red.png")));
                    if(!loginBO.getId().equals(UserId)) {
                        boolean save = notificationBO.save(new NotificationDTO(loginBO.getId(), UserId, postId.getText()));


                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    try {
                        notificationBO.delete(postId.getText());
                        hartReaction.setImage(new Image(new FileInputStream("/home/madushanka/Desktop/Cupid/src/image/heart-white.png")));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });

        proPic = new ImageView();
        proPic.setFitHeight(50);proPic.setFitWidth(50);proPic.setX(3);proPic.setY(9);proPic.setPickOnBounds(true);
        Circle clip = new Circle(47,56,25.0);
        proPic.setClip(clip);
        //proPicImage.setPreserveRatio(true);

        name= new Text();
        name.setX(189);name.setY(70);name.setStrokeType(StrokeType.OUTSIDE);name.setStrokeWidth(0.0);name.setWrappingWidth(170);name.setScaleX(2);name.setScaleY(2);

        date = new Text();
        date.setX(105);date.setY(90);date.setStrokeType(StrokeType.OUTSIDE);date.setStrokeWidth(0.0);date.setWrappingWidth(170);

        borderPane1.setCenter(shareImage);



    }

    public NewProfileModel(BorderPane borderPane1, AnchorPane top, AnchorPane bottom, ImageView shareImage, ImageView proPic, ImageView hartReaction, Text name, Text date, Text postId, String userId) {

        this.borderPane1 = borderPane1;

        this.shareImage = shareImage;
        this.proPic = proPic;
        this.hartReaction = hartReaction;
        this.name = name;
        this.date = date;
        this.postId = postId;
        UserId = userId;
    }


    public BorderPane getBorderPane1() {
        return borderPane1;
    }

    public void setBorderPane1(BorderPane borderPane1) {
        this.borderPane1 = borderPane1;
    }

    public Image getShareImage() {
        return shareImage.getImage();
    }

    public void setShareImage(Image shareImage) {
        this.shareImage.setImage(shareImage);
    }

    public Image getProPic() {
        return proPic.getImage();
    }

    public void setProPic(Image proPic) {
        this.proPic.setImage(proPic);
    }

    public ImageView getHartReaction() {
        return hartReaction;
    }

    public void setHartReaction(String parth) throws FileNotFoundException {
        this.hartReaction.setImage(new Image(new FileInputStream(parth)));
    }

    public String getName() {
        return name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public String getDate() {
        return date.getText();
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public String getPostId() {
        return postId.getText();
    }

    public void setPostId(String postId) {
        this.postId.setText(postId);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
