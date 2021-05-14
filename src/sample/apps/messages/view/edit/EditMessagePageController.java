package sample.apps.messages.view.edit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.apps.messages.listener.EditMessageListener;
import sample.apps.messages.model.Message;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMessagePageController extends PageController implements Initializable {
    EditMessageListener editListener;

    @FXML
    TextArea messageTxt;

    @FXML
    Label editedLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editListener = new EditMessageListener();
        editedLabel.setVisible(false);
    }

    @Override
    public void setMessage(Message message) {
        super.setMessage(message);
        setInformation();
    }

    private void setInformation(){
        messageTxt.setText(message.getContent());
    }

    private String getMessageTxt(){
        return messageTxt.getText();
    }

    public void edit(){
        editListener.listen(message, getMessageTxt());
    }
}
