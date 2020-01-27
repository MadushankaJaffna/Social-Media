package controller;

import bussiness.BOFactory;
import bussiness.BOType;
import bussiness.custom.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import dto.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.*;
import model.NewModelPhotoWall;
import model.NotificationModel;
import model.PostWallModel;
import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.control.Alert.*;
import static javafx.scene.control.ButtonType.*;


public class HomeController {
    public ImageView notificationBell;
    public ImageView img_LogOut;
    public Tab postWall;
    public Tab Notification;
    public Tab photowall;
    public ListView photoWallListViwe;
    public ListView postWallListViwe;
    public ListView notificationListViwe;
    public ImageView profilePic;
    public Text userName;
    public Text userHomeTown;
    public Text userDescription;
    public Text userNameUpload;
    public Text date;
    public JFXButton btn_share;
    public JFXButton btn_cancle;
    public Text adminNote;
    public AnchorPane uploadAnchorPain;
    public BorderPane root;
    public Circle imageRound;
    public ImageView uploadimageButton;
    public ImageView userPhotoUpload;
    public JFXListView searchListViwe;
    public TextField searchBox;
    private FileInputStream sendPhoto;

    public InputStream proPic;


    private LoginBO loginBO = BOFactory.getInstance().getBO(BOType.Login);
    private UserBO userBO = BOFactory.getInstance().getBO(BOType.user);
    private PhotoWallBO photoWallBO = BOFactory.getInstance().getBO(BOType.photoWall);
    private PostWallBO postWallBO =BOFactory.getInstance().getBO(BOType.postWall);
    private NotificationBO notificationBO =BOFactory.getInstance().getBO(BOType.notification);
    private AdminWorkBO adminWorkBO = BOFactory.getInstance().getBO(BOType.adminWork);


    public void initialize() {


        searchListVisible();
        recursivePhoto();
        recursivePost();
        recursiveNotification();
        try {

            List<AdminNoteDTO> note = adminWorkBO.findNote();
            for (AdminNoteDTO data:note) {
                String note1 = data.getNote();
                adminNote.setText(note1);
            }

            String id = loginBO.getId();
            UserDTO userDTO = userBO.find(id);
                  userName.setText(userDTO.getName());
                  userHomeTown.setText(userDTO.getHomeTown());
                  userDescription.setText(userDTO.getDescription());
                  userNameUpload.setText(userDTO.getName());
                  date.setText(String.valueOf(LocalDate.now()));

            Circle clip = new Circle(100,80,74.0);
            profilePic.setClip(clip);

            try{
                proPic = userDTO.getProPic();
                profilePic.setImage(new Image(proPic));
                userPhotoUpload.setImage(new Image(proPic));

            }catch (NullPointerException e){
                System.out.println("User not use Profile picture then system use Default avatar");
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        //search box and related codes
    searchBox.textProperty().addListener(observable -> {
        if(searchBox.getText().isEmpty()){
            searchListViwe.getItems().clear();
            searchListVisible();
        }
        else {
            searchListViwe.setVisible(true);
            ObservableList items = searchListViwe.getItems();
            try {
                List<SearchDTO> search = userBO.search(searchBox.getText() + "%");
                searchListViwe.getItems().clear();
                for (SearchDTO adding:search) {
                    items.add(adding.getName());

                    searchListViwe.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Object selectedItem = searchListViwe.getSelectionModel().getSelectedItem();
                            try {
                                String id = userBO.findId(String.valueOf(selectedItem));
                                VisitProfileController.userIdg =id;
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

                            } catch (Exception e) {
                                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                                Logger.getLogger("").log(Level.SEVERE,null,e);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            } }
    });

    }

    private void recursiveNotification() {
        notificationListViwe.getItems().clear();
        ObservableList items = notificationListViwe.getItems();

        try {
            List<NotificationDTO> notificationDTOS = notificationBO.find(loginBO.getId());
            for (NotificationDTO data:notificationDTOS) {
                UserDTO userDTO = userBO.find(data.getSenderId());

                NotificationModel notification = new NotificationModel();
                try {
                    notification.setProPic(new Image(userDTO.getProPic()));
                }catch (NullPointerException e){
                notification.setProPic(new Image(new FileInputStream("src/image/avatar.png")));
                }
                notification.setSenderName(userDTO.getName());
                notification.setUserId(userDTO.getUserID());

                items.add(0,notification.getMother());

            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    public void img_notificationBellOnClick(MouseEvent mouseEvent) {

    }

    public void img_logOutOnAction(MouseEvent mouseEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you really want to logout??", YES, NO);
        Optional<ButtonType> buttonType =alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)){

            slide("/viwe/loginForm.fxml");
        }
    }

    public void img_proPicOnClick(MouseEvent mouseEvent) {
        slide("/viwe/profile.fxml");

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


    public void btn_shareOnAction(ActionEvent actionEvent) {
        try {
            boolean b = photoWallBO.savePhoto(new PhotoWallDTO(loginBO.getId(), null, sendPhoto, 0, Date.valueOf(LocalDate.now())));
            if(b){
                uploadimageButton.setImage(null);
                System.out.println("Photo save done");
                photoWallListViwe.getItems().clear();

                recursivePhoto();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    public void btn_cancleOnAction(ActionEvent actionEvent) {
        uploadimageButton.setImage(null);
    }

    public void recursivePhoto(){
        ObservableList photoList = photoWallListViwe.getItems();

        try {
            List<PhotoWallDTO> all = photoWallBO.findAll();
            for (PhotoWallDTO data:all) {
                UserDTO userDTO = userBO.find(data.getUserId());


                NewModelPhotoWall adding = new NewModelPhotoWall();
                try {
                    adding.setProPic(new Image(userDTO.getProPic()));
                }catch (NullPointerException e){
                    adding.setProPic(new Image(new FileInputStream("src/image/avatar.png")));
                }
                adding.setName(userDTO.getName());
                adding.setShareImage(new Image(data.getImage()));
                adding.setPostId(data.getPostId());
                adding.setUserId(data.getUserId());

                adding.setDate(String.valueOf(data.getDate()));

                try {

                    NotificationDTO findpost = notificationBO.findpost(data.getPostId());

                    System.out.println(findpost.getSenderId() + " " + loginBO.getId());


                    if (findpost.getSenderId().equals(loginBO.getId())) {
                        adding.setHartReaction("src/image/heart red.png");
                    } else {
                        adding.setHartReaction("src/image/heart-white.png");
                    }

                }catch (NullPointerException e) {
                    adding.setHartReaction("src/image/heart-white.png");
                }

                photoList.add(0,adding.getBorderPane1());

                System.gc();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }

    public void recursivePost(){

       postWallListViwe.getItems().clear();
        ObservableList postList = postWallListViwe.getItems();

        try {
            List<PostWallDTO> all = postWallBO.findAll();
            for (PostWallDTO data:all) {
                UserDTO userDTO = userBO.find(data.getUserId());

                PostWallModel my = new PostWallModel();

                try {
                    my.setProPic(new Image(userDTO.getProPic()));
                }catch (NullPointerException e){
                    my.setProPic(new Image(new FileInputStream("src/image/avatar.png")));
                }
                my.setPostId(data.getPostId());

                my.setDate(String.valueOf(data.getDate()));
                my.setUserID(data.getUserId());
                my.setName(userDTO.getName());
                my.setDescription(data.getDescriotion());

                try {

                    NotificationDTO findpost = notificationBO.findpost(data.getPostId());


                    if (findpost.getSenderId().equals(loginBO.getId())) {
                        my.setHart("src/image/heart red.png");
                    } else {
                        my.setHart("src/image/heart-white.png");
                    }

                }catch (NullPointerException e) {
                    my.setHart("src/image/heart-white.png");
                }
                postList.add(0,my.getMother());

                System.gc();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    public void btn_uploadImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg","*.JPG", "*.gif"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if (file != null) {
            Image image=new Image(file.toURI().toString(),728,500,true,true);
            uploadimageButton.setPreserveRatio(true);
            uploadimageButton.setImage(image);
            uploadAnchorPain.setCenterShape(true);

            try {
                sendPhoto = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
        }
    }

    public void btn_shareApost(ActionEvent actionEvent) {
        Stage popupStage = new Stage();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../viwe/postEditor.fxml"));
            Parent root = fxmlLoader.load();
            Scene popupScene = new Scene(root);
            popupScene.setFill(Color.TRANSPARENT);
            popupStage.setScene(popupScene);
            popupStage.showAndWait();

            recursivePost();

        } catch (IOException e) {
            new Alert(Alert.AlertType.CONFIRMATION,"Something went wrong",ButtonType.OK).show();
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }

    public void imageUploadMouseEntered(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getTarget();
        DropShadow glow = new DropShadow();
        glow.setColor(Color.RED);
        glow.setWidth(10.0D);
        glow.setHeight(10.0D);
        glow.setRadius(20.0D);
        icon.setEffect(glow);
    }

    public void imageUploadMouseExited(MouseEvent mouseEvent) {
        ImageView icon = (ImageView) mouseEvent.getSource();
        icon.setEffect(null);
    }

    public void searchListOnAction(ActionEvent actionEvent) {

    }
    private void searchListVisible(){
        searchListViwe.setVisible(false);
    }
}

