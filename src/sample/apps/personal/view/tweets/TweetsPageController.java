package sample.apps.personal.view.tweets;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import sample.apps.image.listener.SelectImageListener;
import sample.apps.personal.listener.CollectPersonalPostsListener;
import sample.apps.personal.listener.TweetListener;
import sample.apps.post.model.Post;
import sample.apps.post.view.TweetPage;
import sample.apps.profile.listener.CollectUserTweetsListener;
import sample.view.PageController;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TweetsPageController extends PageController implements Initializable {
    CollectPersonalPostsListener tweetsListener;
    TweetListener tweetListener;

    String imagePath = "";

    @FXML
    TextArea tweetTxt;

    @FXML
    Button tweetButton;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tweetsListener = new CollectPersonalPostsListener();
        tweetListener = new TweetListener();
        setInformation();
    }

    private void setInformation(){
        LinkedList<Post> posts = tweetsListener.collectPosts();
        for (Post post : posts) {
            TweetPage tweetPage = new TweetPage();
            tweetPage.getController().setPost(post);
            box.getChildren().add(tweetPage.getPane());
        }
    }

    private String getTweet(){
        return tweetTxt.getText();
    }

    public void tweet(){
        tweetListener.listen(getTweet(), imagePath);
        setInformation();
    }

    public void uploadImage(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            imagePath = file.getName();
            new SelectImageListener().saveImage(file);
        }
    }
}
