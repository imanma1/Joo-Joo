package sample.apps.profile.view.tweets;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import sample.apps.post.model.Post;
import sample.apps.post.view.TweetPage;
import sample.apps.profile.listener.CollectUserTweetsListener;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TweetsPageController extends PageController implements Initializable {
    CollectUserTweetsListener listener;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new CollectUserTweetsListener();
        collectTweets();
    }

    private void collectTweets(){
        LinkedList<Post> posts = listener.collectPosts(user);
        for (Post post : posts) {
            TweetPage tweetPage = new TweetPage();
            tweetPage.getController().setPost(post);
            box.getChildren().add(tweetPage.getPane());
        }
    }
}
