package sample.apps.profile.view.header;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.apps.authentication.model.User;
import sample.apps.profile.event.UserInformationEvent;
import sample.apps.profile.listener.*;
import sample.listener.UserListener;
import sample.view.PageController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ProfileHeaderController extends PageController implements Initializable {

    private final LinkedList<UserListener> listeners = new LinkedList<>();

    @FXML
    ImageView avatar;

    @FXML
    Label usernameLabel;

    @FXML
    Label lastSeenLabel;

    @FXML
    Label bioLabel;

    @FXML
    Label numberOfFollowersLabel;

    @FXML
    Label numberOfFollowingLabel;

    @FXML
    Label numberOfPostsLabel;

    @FXML
    Button followBtn;

    @FXML
    Button sendMessageBtn;

    @FXML
    Button muteBtn;

    @FXML
    Button blockBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeners.add(new FollowListener());
        listeners.add(new MuteListener());
        listeners.add(new BlockListener());
    }

    @Override
    public void setUser(User user){
        this.user = user;
        setInformation();
        setButtons();
    }

    private void setButtons(){
        setFollowButton();
        setMuteButton();
        setBlockButton();
    }

    private void setFollowButton(){
        followBtn.setText(new IsFollowButtonPressedListener().getTextOfLabel(user));
    }

    private void setMuteButton(){
        muteBtn.setText(new IsMuteButtonPressedListener().getTextOfLabel(user));
    }

    private void setBlockButton(){
        blockBtn.setText(new IsBlockButtonPressedListener().getTextOfLabel(user));
    }

    private void setInformation(){
        UserInformationEvent data = new UserInformationListener().collectInformation(user);
        setUsername(data);
        setLastSeen(data);
        setBio(data);
        setNumberOfFollowers(data);
        setNumberOfFollowing(data);
        setNumberOfPosts(data);
        setAvatar(data);
    }

    private void setUsername(UserInformationEvent data){
        usernameLabel.setText(data.getUsername());
    }

    private void setLastSeen(UserInformationEvent data){
        lastSeenLabel.setText(data.getLastSeen());
    }

    private void setBio(UserInformationEvent data){
        bioLabel.setText(data.getBio());
    }

    private void setAvatar(UserInformationEvent data){
        if (data.isHasImage()){
            try {
                avatar.setImage(new Image(new FileInputStream(data.getImageFile())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setNumberOfFollowers(UserInformationEvent data){
        numberOfFollowersLabel.setText(data.getNumberOfFollowers() + " Followers");
    }

    private void setNumberOfFollowing(UserInformationEvent data){
        numberOfFollowingLabel.setText(data.getNumberOfFollowing() + " Following");
    }

    private void setNumberOfPosts(UserInformationEvent data){
        numberOfPostsLabel.setText(data.getNumberOfPosts() + " Posts");
    }

    public void follow(){
        listen("Follow");
        setInformation();
        setButtons();
    }

    public void mute(){
        listen("Mute");
        setButtons();
    }

    public void block(){
        listen("Block");
        setInformation();
        setButtons();
    }

    private void listen(String command){
        for (UserListener listener : listeners) {
            listener.listen(user, command);
        }
    }
}
