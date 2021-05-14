package sample.apps.messages.view.add;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import sample.apps.messages.listener.AddMemberListener;
import sample.apps.messages.listener.CollectUsernamesForAddingUserListener;
import sample.apps.messages.model.Group;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AddMemberPageController extends PageController implements Initializable {
    CollectUsernamesForAddingUserListener usernamesListener;
    AddMemberListener addListener;

    @FXML
    ListView<String> listView;

    @FXML
    Label addedLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamesListener = new CollectUsernamesForAddingUserListener();
        addListener = new AddMemberListener();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        addedLabel.setVisible(false);
    }

    @Override
    public void setGroup(Group group) {
        super.setGroup(group);
        setInformation();
    }

    private void setInformation(){
        LinkedList<String> usernames = usernamesListener.collectUsernames(group);
        listView.getItems().addAll(usernames);
    }

    public void addMember(){
        ObservableList<String> usernames = listView.getSelectionModel().getSelectedItems();
        for (String username : usernames) {
            addListener.addMember(group, username);
        }
        addedLabel.setVisible(true);
        setInformation();
    }
}
