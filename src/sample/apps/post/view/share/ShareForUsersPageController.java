package sample.apps.post.view.share;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import sample.apps.messages.listener.CollectUsernamesListener;
import sample.apps.post.listener.ShareForUsersListener;
import sample.apps.post.model.Post;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ShareForUsersPageController extends PageController implements Initializable {
    CollectUsernamesListener usernamesListener;
    ShareForUsersListener shareListener;

    @FXML
    ListView<String> listView;

    @FXML
    Label sentLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamesListener = new CollectUsernamesListener();
        shareListener = new ShareForUsersListener();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        sentLabel.setVisible(false);
        setInformation();
    }

    @Override
    public void setPost(Post post) {
        super.setPost(post);
    }

    private void setInformation(){
        LinkedList<String> usernames = usernamesListener.colletNames();
        listView.getItems().addAll(usernames);
    }

    public void share(){
        ObservableList<String> usernames = listView.getSelectionModel().getSelectedItems();
        for (String username : usernames) {
            shareListener.listen(post, username);
        }
        sentLabel.setVisible(true);
    }
}
