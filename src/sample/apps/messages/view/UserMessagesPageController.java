package sample.apps.messages.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import sample.apps.authentication.model.User;
import sample.apps.image.listener.SelectImageListener;
import sample.apps.messages.event.MessagesInformationEvent;
import sample.apps.messages.listener.SendUserMessageListener;
import sample.apps.messages.listener.UserMessagesInformationListener;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Message;
import sample.apps.messages.model.Messages;
import sample.view.PageController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMessagesPageController extends PageController implements Initializable {
    UserMessagesInformationListener listener;
    SendUserMessageListener messageListener;

    String imagePath = "";

    @FXML
    Label nameLabel;

    @FXML
    TextArea messageTxt;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new UserMessagesInformationListener();
        messageListener = new SendUserMessageListener();
    }

    @Override
    public void setMessages(Messages messages) {
        super.setMessages(messages);
        setInformation();
    }

    private void setInformation(){
        MessagesInformationEvent data = listener.collectInformation(messages);
        nameLabel.setText(data.getUsername());
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
        messageListener.listen(messages, getMessageTxt(), imagePath);
        box.getChildren().removeAll();
        setInformation();
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
