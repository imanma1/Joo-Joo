package sample.apps.messages.view.list;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.apps.authentication.model.User;
import sample.apps.messages.event.MessagesInformationEvent;
import sample.apps.messages.listener.UserMessagesInformationListener;
import sample.apps.messages.model.Messages;
import sample.apps.personal.view.lists.MainPageToUserInListListener;
import sample.apps.profile.event.UserInformationEvent;
import sample.apps.profile.listener.UserInformationListener;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;


public class UserInMessagesListController extends PageController implements Initializable {
    UserMessagesInformationListener listener;
    MainPageToUserMessagesInListListener pageListener;

    @FXML
    Label usernameLabel;

    @FXML
    Button showMessagesBtn;

    @FXML
    Label unseenMessagesLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new UserMessagesInformationListener();
        pageListener = new MainPageToUserMessagesInListListener();
        unseenMessagesLabel.setVisible(false);
    }

    @Override
    public void setMessages(Messages messages) {
        super.setMessages(messages);
        setInformation();
    }

    private void setInformation(){
        MessagesInformationEvent data = listener.collectInformation(messages);
        usernameLabel.setText(data.getUsername());
        if (!data.isSeen()) unseenMessagesLabel.setVisible(true);
    }

    public void showMessages(){
        pageListener.listen(messages, "messages");
    }
}
