package sample.apps.post.view.share;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import sample.apps.messages.listener.CollectGroupsNamesListener;
import sample.apps.messages.listener.ForwardToGroupListener;
import sample.apps.post.listener.ShareForGroupsListener;
import sample.apps.post.model.Post;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ShareForGroupsListPageController extends PageController implements Initializable {
    CollectGroupsNamesListener groupsNamesListener;
    ShareForGroupsListener shareListener;

    @FXML
    ListView<String> listView;

    @FXML
    Label sentLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupsNamesListener = new CollectGroupsNamesListener();
        shareListener = new ShareForGroupsListener();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        sentLabel.setVisible(false);
        setInformation();
    }

    @Override
    public void setPost(Post post) {
        super.setPost(post);
    }

    private void setInformation(){
        LinkedList<String> groupNames = groupsNamesListener.colletNames();
        listView.getItems().addAll(groupNames);
    }

    public void share(){
        ObservableList<String> groupNames = listView.getSelectionModel().getSelectedItems();
        for (String groupName : groupNames) {
            shareListener.listen(post, groupName);
        }
        sentLabel.setVisible(true);
    }
}
