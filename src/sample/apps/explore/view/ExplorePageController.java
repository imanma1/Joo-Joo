package sample.apps.explore.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.model.User;
import sample.apps.explore.listener.CollectPopularTweetsListener;
import sample.apps.explore.listener.SearchUserListener;
import sample.apps.personal.view.lists.MainPageToUserInListListener;
import sample.apps.personal.view.tweets.TweetsPage;
import sample.apps.post.model.Post;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ExplorePageController extends PageController implements Initializable {
    CollectPopularTweetsListener collectorListener;
    SearchUserListener searchListener;
    MainPageToUserInListListener pageListener;

    @FXML
    TextField usernameTxt;

    @FXML
    Button searchBtn;

    @FXML
    Label errorLabel;

    @FXML
    VBox box;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectorListener = new CollectPopularTweetsListener();
        searchListener = new SearchUserListener();
        pageListener = new MainPageToUserInListListener();
        errorLabel.setText("");
        setInformation();
    }

    private void setInformation(){
        LinkedList<Post> posts = collectorListener.collectPosts();
        for (Post post : posts) {
            TweetsPage tweetsPage = new TweetsPage();
            tweetsPage.getController().setPost(post);
            box.getChildren().addAll(tweetsPage.getPane());
        }
    }

    private String getUsername(){
        return usernameTxt.getText();
    }

    public void search(){
        try {
            User user = searchListener.search(getUsername());
            pageListener.listen(user, "profile");
        } catch (UserDoesNotExistException e){
            errorLabel.setText(e.getMessage());
        }
    }
}
