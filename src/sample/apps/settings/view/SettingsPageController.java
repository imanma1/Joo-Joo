package sample.apps.settings.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.apps.authentication.exception.WrongPasswordException;
import sample.apps.authentication.model.LastSeenSetting;
import sample.apps.settings.event.ChangePasswordEvent;
import sample.apps.settings.listener.*;
import sample.listener.StringListener;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SettingsPageController extends PageController implements Initializable {
    LinkedList<StringListener> listeners = new LinkedList<>();
    ChangePasswordListener changePasswordListener;

    @FXML
    private ChoiceBox<String> lastSeenBox;

    private final String[] lastSeenOptions = {"Everybody", "Followers", "Nobody"};

    @FXML
    private ChoiceBox<String> accountPrivacyBox;

    private final String[] accountPrivacyOptions = {"Public", "Private"};

    @FXML
    private TextField oldPassTxt;

    @FXML
    private TextField newPassTxt;

    @FXML
    private Button changePassBtn;

    @FXML
    private Button deactivateAccountBtn;

    @FXML
    private Button deleteAccountBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeners.add(new AccountPrivacySettingListener());
        listeners.add(new LastSeenSettingListener());
        listeners.add(new DeactivateAccountListener());
        listeners.add(new DeleteAccountListener());
        listeners.add(new LogoutListener());
        changePasswordListener = new ChangePasswordListener();

        lastSeenBox.getItems().addAll(lastSeenOptions);
        accountPrivacyBox.getItems().addAll(accountPrivacyOptions);
        lastSeenBox.setOnAction(this::getLastSeenSetting);
        accountPrivacyBox.setOnAction(this::getAccountPrivacySetting);
    }

    public void getLastSeenSetting(ActionEvent event){
        listen(lastSeenBox.getValue());
    }

    public void getAccountPrivacySetting(ActionEvent event){
        listen(accountPrivacyBox.getValue());
    }

    public void changePassword(){
        ChangePasswordEvent changePasswordEvent =
                new ChangePasswordEvent(
                        this,
                        oldPassTxt.getText(),
                        newPassTxt.getText()
                );
        try {
            changePasswordListener.eventOccurred(changePasswordEvent);
        } catch (WrongPasswordException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void deactivateAccount(){
        listen("Deactivate");
    }

    public void deleteAccount(){
        listen("Delete");
    }

    public void logout(){
        listen("Logout");
    }

    private void listen(String command){
        for (StringListener listener : listeners) {
            listener.listen(command);
        }
    }
}
