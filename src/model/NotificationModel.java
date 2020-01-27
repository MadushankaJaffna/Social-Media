package model;

import controller.VisitProfileController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class NotificationModel {
    private AnchorPane mother;
    private Text senderName;
    private ImageView proPicImage;
    private String UserId;

    public NotificationModel() {

        this.mother =new AnchorPane();

        mother.setPrefSize(600,55);

        this.senderName = new Text();
        senderName.setX(55);senderName.setY(20);senderName.setStrokeType(StrokeType.OUTSIDE);senderName.setStrokeWidth(0.0);senderName.setWrappingWidth(170);

        this.proPicImage = new ImageView();
        Circle clip = new Circle(25,27,20.0);
        proPicImage.setClip(clip);
       // proPicImage.setPreserveRatio(true);
        proPicImage.setFitHeight(50);proPicImage.setFitWidth(50);proPicImage.setX(3);proPicImage.setY(2);proPicImage.setPickOnBounds(true);

        proPicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
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

        mother.getChildren().add(proPicImage);
        mother.getChildren().add(senderName);

    }

    public NotificationModel(AnchorPane mother, Text senderName, ImageView proPic) {
        this.mother = mother;
        this.senderName = senderName;
        this.proPicImage = proPic;
    }

    public AnchorPane getMother() {
        return mother;
    }

    public void setMother(AnchorPane mother) {
        this.mother = mother;
    }

    public String getSenderName() {
        return senderName.getText();
    }

    public void setSenderName(String senderName) {
        this.senderName.setText(senderName + " reacted to a post you shared");
    }

    public Image getProPic() {
        return proPicImage.getImage();
    }

    public void setProPic(Image proPic) {
        this.proPicImage.setImage(proPic);
    }
    public void setUserId(String uid){this.UserId =uid;}

}
