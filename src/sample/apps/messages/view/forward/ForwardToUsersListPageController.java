package sample.apps.messages.view.forward;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import sample.apps.messages.listener.CollectUsernamesListener;
import sample.apps.messages.listener.ForwardToUserListener;
import sample.apps.messages.model.Message;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ForwardToUsersListPageController extends PageController implements Initializable {
    CollectUsernamesListener usernamesListener;
    ForwardToUserListener forwardListener;

    @FXML
    ListView<String> listView;

    @FXML
    Label sentLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamesListener = new CollectUsernamesListener();
        forwardListener = new ForwardToUserListener();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        sentLabel.setVisible(false);
        setInformation();
    }

    @Override
    public void setMessage(Message message) {
        super.setMessage(message);
    }

    private void setInformation(){
        LinkedList<String> usernames = usernamesListener.colletNames();
        listView.getItems().addAll(usernames);
    }

    public void forward(){
        ObservableList<String> usernames = listView.getSelectionModel().getSelectedItems();
        for (String username : usernames) {
            forwardListener.listen(message, username);
        }
        sentLabel.setVisible(true);
    }
}
