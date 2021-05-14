package sample.apps.notifications.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.apps.notifications.listener.CollectNotificationListener;
import sample.view.MainPageListener;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationsPageController extends PageController implements Initializable {
    private CollectNotificationListener listener;
    private MainPageListener pageListener;

    @FXML
    VBox box;

    @FXML
    Button RequestBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pageListener = new MainPageListener();
        listener = new CollectNotificationListener();
        setInformation();
    }

    private void setInformation(){
        for (String notification : listener.collectNotifications()) {
            Label label = new Label(notification);
            label.setFont(Font.font("Constantia", 12));
            box.getChildren().add(label);
        }
    }

    public void requests(){
        pageListener.listen("requests");
    }
}
