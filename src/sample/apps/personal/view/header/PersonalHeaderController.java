package sample.apps.personal.view.header;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.apps.personal.event.PersonalInformationEvent;
import sample.apps.personal.listener.PersonalInformationListener;
import sample.apps.profile.event.UserInformationEvent;
import sample.listener.StringListener;
import sample.view.MainPageListener;
import sample.view.PageController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class PersonalHeaderController extends PageController implements Initializable {
    private MainPageListener pageListener;

    @FXML
    ImageView avatar;

    @FXML
    Label usernameLabel;

    @FXML
    Label birthLabel;

    @FXML
    Label bioLabel;

    @FXML
    Label numberOfFollowersLabel;

    @FXML
    Label numberOfFollowingLabel;

    @FXML
    Label numberOfPostsLabel;

    @FXML
    Button editProfileBtn;

    @FXML
    Button infoBtn;

    @FXML
    Button followersBtn;

    @FXML
    Button followingBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInformation();
        pageListener = new MainPageListener();
    }

    private void setInformation(){
        PersonalInformationEvent data = new PersonalInformationListener().collectInformation();
        setUsername(data);
        setBio(data);
        setBirth(data);
        setNumberOfFollowers(data);
        setNumberOfFollowing(data);
        setNumberOfPosts(data);
        setImage(image);
    }

    private void setUsername(PersonalInformationEvent data){
        usernameLabel.setText(data.getUsername());
    }

    private void setBirth(PersonalInformationEvent data){
        birthLabel.setText(data.getBirth());
    }

    private void setBio(PersonalInformationEvent data){
        bioLabel.setText(data.getBio());
    }

    private void setNumberOfFollowers(PersonalInformationEvent data){
        numberOfFollowersLabel.setText(data.getNumberOfFollowers() + " Followers");
    }

    private void setNumberOfFollowing(PersonalInformationEvent data){
        numberOfFollowingLabel.setText(data.getNumberOfFollowing() + " Following");
    }

    private void setNumberOfPosts(PersonalInformationEvent data){
        numberOfPostsLabel.setText(data.getNumberOfPosts() + " Posts");
    }

    private void setAvatar(PersonalInformationEvent data){
        if (data.isHasImage()){
            try {
                avatar.setImage(new Image(new FileInputStream(data.getImageFile())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void editProfile(){
        pageListener.listen("edit");
    }

    public void info(){
        pageListener.listen("info");
    }

    public void followers(){
        pageListener.listen("followers");
    }

    public void following(){
        pageListener.listen("following");
    }
}
