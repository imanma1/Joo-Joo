package sample.apps.personal.view.lists;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import sample.apps.authentication.model.User;
import sample.apps.personal.listener.CollectFollowersListener;
import sample.apps.personal.listener.CollectFollowingListener;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class FollowingPageController extends PageController implements Initializable {
    CollectFollowingListener followingListener;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        followingListener = new CollectFollowingListener();
        showUsers(followingListener.collectUsers());
    }

    private void showUsers(LinkedList<User> users){
        for (User user : users) {
            UserInList userPage = new UserInList();
            userPage.getController().setUser(user);
            box.getChildren().add(userPage.getPane());
        }
    }
}
