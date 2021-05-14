package sample.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuSideBarController extends PageController implements Initializable {
    MainPageListener listener;

    @FXML
    private Button backBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button exploreBtn;

    @FXML
    private Button notificationsBtn;

    @FXML
    private Button messagesBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button settingsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new MainPageListener();
    }

    public void back(){
        listener.listen("back");
    }

    public void home(){
        listener.listen("home");
    }

    public void explore(){
        listener.listen("explore");
    }

    public void notification(){
        listener.listen("notifications");
    }

    public void message(){
        listener.listen("messages");
    }

    public void personalProfile(){
        listener.listen("profile");
    }

    public void setting(){
        listener.listen("settings");
    }
}
