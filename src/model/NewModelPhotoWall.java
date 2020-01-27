package model;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.LoginBO;
import bussiness.custom.NotificationBO;
import controller.VisitProfileController;
import dto.NotificationDTO;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

public class NewModelPhotoWall {
    private AnchorPane mother;
    private BorderPane borderPane1;
    private AnchorPane top;
    private AnchorPane bottom;
    private ImageView shareImage;
    private ImageView proPic;
    private ImageView hartReaction;
    private Text name;
    private Text date;
    private String postId;
    private String UserId;

    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);
    private NotificationBO notificationBO = BOFactory.getInstance().getBO(BOType.notification);

    public NewModelPhotoWall() {
        mother =new AnchorPane();
        mother.setPrefSize(600,680);

        borderPane1=new BorderPane();
        borderPane1.prefHeight(600);borderPane1.prefWidth(680);borderPane1.setLayoutX(1);
        borderPane1.setLayoutY(600);

        top=new AnchorPane();
        top.prefHeight(600);top.prefWidth(40);

        bottom = new AnchorPane();
        bottom.prefHeight(600);bottom.prefWidth(40);

        shareImage=new ImageView();
        shareImage.setPreserveRatio(true);
        shareImage.setFitHeight(550);shareImage.setFitWidth(700);
        shareImage.setX(0);shareImage.setY(0);shareImage.setPickOnBounds(true);

        hartReaction = new ImageView();
        hartReaction.setFitHeight(40);hartReaction.setFitWidth(40);hartReaction.setX(10);hartReaction.setY(10);hartReaction.setPickOnBounds(true);
        hartReaction.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    hartReaction.setImage(new Image(new FileInputStream("src/image/heart red.png")));
                    if(!loginBO.getId().equals(UserId)) {
                        boolean save = notificationBO.save(new NotificationDTO(loginBO.getId(), UserId, postId));


                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    try {
                        notificationBO.delete(postId);
                        hartReaction.setImage(new Image(new FileInputStream("src/image/heart-white.png")));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        });

        proPic = new ImageView();
        proPic.setFitHeight(90);proPic.setFitWidth(90);proPic.setX(3);proPic.setY(9);proPic.setPickOnBounds(true);
        Circle clip = new Circle(47,56,40.0);
        proPic.setClip(clip);
        //proPicImage.setPreserveRatio(true);
        proPic.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                VisitProfileController.userIdg = UserId;

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


        name= new Text();
        name.setX(189);name.setY(70);name.setStrokeType(StrokeType.OUTSIDE);name.setStrokeWidth(0.0);name.setWrappingWidth(170);name.setScaleX(2);name.setScaleY(2);

        date = new Text();
        date.setX(105);date.setY(90);date.setStrokeType(StrokeType.OUTSIDE);date.setStrokeWidth(0.0);date.setWrappingWidth(170);

        borderPane1.setTop(top);
        borderPane1.setBottom(bottom);
        borderPane1.setCenter(shareImage);

        top.getChildren().add(proPic);
        top.getChildren().add(name);
        top.getChildren().add(date);

        bottom.getChildren().add(hartReaction);

        mother.getChildren().add(borderPane1);

    }

    public NewModelPhotoWall(BorderPane borderPane1, AnchorPane top, AnchorPane bottom, ImageView shareImage, ImageView proPic, ImageView hartReaction, Text name, Text date, String postId, String userId) {
        this.borderPane1 = borderPane1;
        this.top = top;
        this.bottom = bottom;
        this.shareImage = shareImage;
        this.proPic = proPic;
        this.hartReaction = hartReaction;
        this.name = name;
        this.date = date;
        this.postId = postId;
        UserId = userId;
    }

    public AnchorPane getMother() {
        return mother;
    }

    public void setMother(AnchorPane top) {
        this.mother = top;
    }

    public BorderPane getBorderPane1() {
        return borderPane1;
    }

    public void setBorderPane1(BorderPane borderPane1) {
        this.borderPane1 = borderPane1;
    }

    public AnchorPane getTop() {
        return top;
    }

    public void setTop(AnchorPane top) {
        this.top = top;
    }

    public AnchorPane getBottom() {
        return bottom;
    }

    public void setBottom(AnchorPane bottom) {
        this.bottom = bottom;
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
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
