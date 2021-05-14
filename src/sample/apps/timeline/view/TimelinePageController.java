package sample.apps.timeline.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import sample.apps.post.model.Post;
import sample.apps.post.view.TweetPage;
import sample.apps.timeline.listener.CollectTweetsListener;
import sample.listener.StringListener;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TimelinePageController extends PageController implements Initializable {
    LinkedList<StringListener> listeners = new LinkedList<>();
    CollectTweetsListener collectTweetsListener;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectTweetsListener = new CollectTweetsListener();
        showTweets(collectTweetsListener.collectPosts());
    }

    public void showTweets(LinkedList<Post> tweets){
        for (Post post : tweets) {
            TweetPage tweetPage = new TweetPage();
            tweetPage.getController().setPost(post);
            box.getChildren().add(tweetPage.getPane());
        }
    }
}
