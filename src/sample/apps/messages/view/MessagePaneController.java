package sample.apps.messages.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.apps.image.listener.NewImagePageListener;
import sample.apps.messages.event.MessageInformationEvent;
import sample.apps.messages.listener.CanEditMessageListener;
import sample.apps.messages.listener.MessageInformationListener;
import sample.apps.messages.listener.MessageOptionsListener;
import sample.apps.messages.model.Message;
import sample.view.PageController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessagePaneController extends PageController implements Initializable {
    MessageInformationListener listener;
    MessageOptionsListener optionsListener;
    MainPageToMessagePaneListener pageListener;

    @FXML
    ImageView avatar;

    @FXML
    Label usernameLabel;

    @FXML
    Label messageLabel;

    @FXML
    Label dateLabel;

    @FXML
    Button imageBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new MessageInformationListener();
        optionsListener = new MessageOptionsListener();
        pageListener = new MainPageToMessagePaneListener();
    }

    @Override
    public void setMessage(Message message) {
        super.setMessage(message);
        setInformation();
    }

    private void setInformation(){
        MessageInformationEvent data = listener.collectInformation(message);
        setUsername(data);
        setImageBtn(data);
        messageLabel.setText(data.getContent());
        dateLabel.setText(data.getDate());
    }

    private void setUsername(MessageInformationEvent data){
        if (data.isForwarded()){
            usernameLabel.setText(data.getUsername() + " (" + data.getForwardedFrom() + ")");
        } else {
            usernameLabel.setText(data.getUsername());
        }
    }

    private void setImageBtn(MessageInformationEvent data){
        if (!data.isHasImage()){
            imageBtn.setVisible(false);
        }
    }

    public void messageOption(){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem edit = new MenuItem("Edit");
        edit.setOnAction(this::editOption);
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(this::deleteOption);
        MenuItem forward = new MenuItem("Forward");
        forward.setOnAction(this::forwardOption);
        contextMenu.getItems().addAll(forward, edit, delete);
    }

    private void editOption(ActionEvent event){
        ///
        if (new CanEditMessageListener().canEdit(message)){
            pageListener.listen(message, "edit");
            setInformation();
        }
    }

    private void deleteOption(ActionEvent event){
        optionsListener.listen(message, "delete");
        setInformation();
    }

    private void forwardOption(ActionEvent event){
        pageListener.listen(message, "forward");
    }

    public void showImage(){
        MessageInformationEvent data = listener.collectInformation(message);
        if (data.isHasImage()){
            try {
                Image image = new Image(new FileInputStream(data.getImageFile()));
                new NewImagePageListener().newImagePage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
