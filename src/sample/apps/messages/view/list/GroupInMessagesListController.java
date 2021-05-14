package sample.apps.messages.view.list;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.apps.messages.event.GroupInformationEvent;
import sample.apps.messages.listener.GroupInformationListener;
import sample.apps.messages.model.Group;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupInMessagesListController extends PageController implements Initializable {
    GroupInformationListener listener;
    MainPageToGroupInListListener pageListener;

    @FXML
    Label groupNameLabel;

    @FXML
    Button showMessagesBtn;

    @FXML
    Label unseenMessagesLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new GroupInformationListener();
        pageListener = new MainPageToGroupInListListener();
        unseenMessagesLabel.setVisible(false);
    }

    @Override
    public void setGroup(Group group) {
        super.setGroup(group);
        setInformation();
    }

    private void setInformation(){
        GroupInformationEvent data = listener.collectInformation(group);
        groupNameLabel.setText(data.getGroupName());
        if (!data.isSeen()) unseenMessagesLabel.setVisible(true);
    }

    public void showMessages(){
        pageListener.listen(group, "messages");
    }
}
