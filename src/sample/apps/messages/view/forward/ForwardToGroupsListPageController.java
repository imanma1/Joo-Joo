package sample.apps.messages.view.forward;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import sample.apps.messages.listener.CollectGroupsNamesListener;
import sample.apps.messages.listener.ForwardToGroupListener;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ForwardToGroupsListPageController extends PageController implements Initializable {
    CollectGroupsNamesListener groupsNamesListener;
    ForwardToGroupListener forwardListener;

    @FXML
    ListView<String> listView;

    @FXML
    Label sentLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupsNamesListener = new CollectGroupsNamesListener();
        forwardListener = new ForwardToGroupListener();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        sentLabel.setVisible(false);
        setInformation();
    }

    private void setInformation(){
        LinkedList<String> groupNames = groupsNamesListener.colletNames();
        listView.getItems().addAll(groupNames);
    }

    public void forward(){
        ObservableList<String> groupNames = listView.getSelectionModel().getSelectedItems();
        for (String groupName : groupNames) {
            forwardListener.listen(message, groupName);
        }
        sentLabel.setVisible(true);
    }
}
