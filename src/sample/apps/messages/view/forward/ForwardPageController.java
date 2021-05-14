package sample.apps.messages.view.forward;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import sample.apps.messages.model.Message;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class ForwardPageController extends PageController implements Initializable {

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    public void setMessage(Message message) {
        super.setMessage(message);
        users();
    }

    public void users(){
        ForwardToUsersListPage usersListPage = new ForwardToUsersListPage();
        usersListPage.getController().setMessage(message);
        borderPane.setCenter(usersListPage.getPane());
    }

    public void groups(){
        ForwardToGroupsListPage groupsListPage = new ForwardToGroupsListPage();
        groupsListPage.getController().setMessage(message);
        borderPane.setCenter(groupsListPage.getPane());
    }
}
