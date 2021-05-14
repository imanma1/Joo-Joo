package sample.apps.personal.view.lists;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.apps.authentication.model.User;
import sample.apps.profile.event.UserInformationEvent;
import sample.apps.profile.listener.UserInformationListener;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInListController extends PageController implements Initializable {
    UserInformationListener listener;
    MainPageToUserInListListener pageListener;

    @FXML
    Button showProfileBtn;

    @FXML
    Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new UserInformationListener();
        pageListener = new MainPageToUserInListListener();
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
        setInformation();
    }

    public void setInformation(){
        UserInformationEvent data = listener.collectInformation(user);
        usernameLabel.setText(data.getUsername());
    }

    public void showProfile(){
        pageListener.listen(user, "profile");
    }
}
