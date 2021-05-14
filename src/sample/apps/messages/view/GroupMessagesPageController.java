package sample.apps.messages.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import sample.apps.authentication.model.User;
import sample.apps.image.listener.SelectImageListener;
import sample.apps.messages.event.GroupInformationEvent;
import sample.apps.messages.listener.GroupInformationListener;
import sample.apps.messages.listener.SendGroupMessageListener;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Message;
import sample.view.PageController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupMessagesPageController extends PageController implements Initializable {
    GroupInformationListener listener;
    SendGroupMessageListener messageListener;
    MainPageToGroupMessagesPageListener pageListener;

    String imagePath = "";

    @FXML
    Label nameLabel;

    @FXML
    TextArea messageTxt;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new GroupInformationListener();
        messageListener = new SendGroupMessageListener();
    }

    @Override
    public void setGroup(Group group) {
        super.setGroup(group);
        setInformation();
    }

    private void setInformation(){
        GroupInformationEvent data = listener.collectInformation(group);
        nameLabel.setText(data.getGroupName());
        for (Message message : data.getMessages()) {
            MessagePane messagePane = new MessagePane();
            messagePane.getController().setMessage(message);
            box.getChildren().add(messagePane.getPane());
        }
    }

    private String getMessageTxt(){
        return messageTxt.getText();
    }

    public void send(){
        messageListener.listen(group, getMessageTxt(), imagePath);
        box.getChildren().removeAll();
        setInformation();
    }

    public void addMembers(){
        pageListener.listen(group, "add");
    }

    public void uploadImage(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            imagePath = file.getName();
            new SelectImageListener().saveImage(file);
        }
    }
}
