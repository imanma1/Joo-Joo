package sample.apps.post.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.apps.image.listener.NewImagePageListener;
import sample.apps.post.event.PostInformationEvent;
import sample.apps.post.exception.AccountIsPrivateException;
import sample.apps.post.listener.*;
import sample.apps.post.model.Post;
import sample.listener.PostListener;
import sample.view.PageController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TweetPageController extends PageController implements Initializable {

    private final LinkedList<PostListener> listeners = new LinkedList<>();

    @FXML
    ImageView avatar;

    @FXML
    Label usernameLabel;

    @FXML
    Label tweetLabel;

    @FXML
    Label dateLabel;

    @FXML
    Label numberOfLikesLabel;

    @FXML
    Label numberOfRetweetsLabel;

    @FXML
    Label numberOfRepliesLabel;

    @FXML
    Button imageBtn;

    @FXML
    Button repliesBtn;

    @FXML
    Button likeBtn;

    @FXML
    Button retweetBtn;

    @FXML
    Button showProfileBtn;

    @FXML
    Button shareBtn;

    @FXML
    Button reportBtn;

    @FXML
    Label errorLabel;

    @Override   
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeners.add(new LikeListener());
        listeners.add(new RetweetListener());
        listeners.add(new ReportListener());
        listeners.add(new MainPageToTweetListener());
        errorLabel.setVisible(false);
    }

    @Override
    public void setPost(Post post) {
        this.post = post;
        setButtons();
        setInformation();
    }

    private void setButtons(){
        setLikeButton();
        setRetweetButton();
        setImageBtn();
    }

    private void setImageBtn(){
        PostInformationEvent data = new PostInformationListener().collectPostInformation(post);
        if (!data.isHasImage()){
            imageBtn.setVisible(false);
        }
    }

    private void setLikeButton(){
        likeBtn.setText(new IsLikeButtonPressedListener().getTextOfLabel(post));
    }

    private void setRetweetButton(){
        retweetBtn.setText(new IsRetweetButtonPressedListener().getTextOfLabel(post));
    }

    private void setInformation(){
        PostInformationEvent data = new PostInformationListener().collectPostInformation(post);
        setContent(data);
        setUsername(data);
        setDate(data);
        setNumberOfLikes(data);
        setNumberOfRetweets(data);
        setNumberOfReplies(data);
    }

    private void setContent(PostInformationEvent data){
        tweetLabel.setText(data.getTweet());
    }

    private void setUsername(PostInformationEvent data){
        usernameLabel.setText("@" + data.getUsername());
    }

    private void setDate(PostInformationEvent data){
        dateLabel.setText(data.getDate());
    }

    private void setAvatar(PostInformationEvent data){
        if (data.isHasProfileImage()){
            try {
                avatar.setImage(new Image(new FileInputStream(data.getProfileImageFile())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setNumberOfLikes(PostInformationEvent data){
        numberOfLikesLabel.setText(data.getNumberOfLikes() + " Likes");
    }

    private void setNumberOfRetweets(PostInformationEvent data){
        numberOfRetweetsLabel.setText(data.getNumberOfRetweets() + " Retweets");
    }

    private void setNumberOfReplies(PostInformationEvent data){
        numberOfRepliesLabel.setText(data.getNumberOfReplies() + " Replies");
    }

    public void replies(){
        listen("replies");
    }

    public void like(){
        listen("Like");
        setButtons();
        setInformation();
    }

    public void retweet(){
        listen("Retweet");
        setButtons();
        setInformation();
    }

    public void showImage(){
        PostInformationEvent data = new PostInformationListener().collectPostInformation(post);
        if (data.isHasImage()){
            try {
                Image image = new Image(new FileInputStream(data.getImageFile()));
                new NewImagePageListener().newImagePage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void showProfile(){
        listen("profile");
    }

    public void share(){
        listen("share");
    }

    public void report(){
        listen("Report");
    }

    public void listen(String command){
        try {
            for (PostListener listener : listeners) {
                listener.listen(post, command);
            }
        } catch (AccountIsPrivateException e){
            errorLabel.setVisible(true);
        }
    }
}


