package sample.apps.messages.view.list;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sample.apps.messages.listener.CollectGroupsMessagesListener;
import sample.apps.messages.listener.CollectUsersMessagesListener;
import sample.apps.messages.listener.NewGroupListener;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Messages;
import sample.apps.messages.view.GroupMessagesPage;
import sample.apps.messages.view.UserMessagesPage;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MessagesListPageController extends PageController implements Initializable {
    CollectUsersMessagesListener messagesListener;
    CollectGroupsMessagesListener groupsListener;
    NewGroupListener newGroupListener;

    @FXML
    TextField groupNameTxt;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messagesListener = new CollectUsersMessagesListener();
        groupsListener = new CollectGroupsMessagesListener();
        newGroupListener = new NewGroupListener();
        setUsersMessagesInformation();
    }

    private void setUsersMessagesInformation(){
        LinkedList<Messages> messages = messagesListener.collectMessages();
        for (Messages userMessages : messages) {
            UserInMessagesList messagesPage = new UserInMessagesList();
            messagesPage.getController().setMessages(userMessages);
            box.getChildren().add(messagesPage.getPane());
        }
    }

    private void setGroupsMessagesInformation(){
        LinkedList<Group> groups = groupsListener.collectGroups();
        for (Group group : groups) {
            GroupInMessagesList messagesPage = new GroupInMessagesList();
            messagesPage.getController().setGroup(group);
            box.getChildren().add(messagesPage.getPane());
        }
    }

    public void users(){
        box.getChildren().removeAll();
        setUsersMessagesInformation();
    }

    public void groups(){
        box.getChildren().removeAll();
        setGroupsMessagesInformation();
    }

    private String getGroupName(){
        return groupNameTxt.getText();
    }

    public void newGroup(){
        if (!getGroupName().isEmpty()){
            newGroupListener.listen(getGroupName());
        }
    }
}
