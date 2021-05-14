package sample.apps.authentication.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.apps.authentication.event.AccessFormEvent;
import sample.apps.authentication.listener.LoginFormListener;
import sample.apps.authentication.listener.RegistrationFormListener;
import sample.view.MainPage;
import sample.view.MainPageListener;
import sample.view.Page;
import sample.view.PageController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccessPageController extends PageController implements Initializable {
    RegistrationFormListener registrationFormListener;
    LoginFormListener loginFormListener;
    MainPageListener pageListener;

    @FXML
    private Button logInBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registrationFormListener = new RegistrationFormListener();
        loginFormListener = new LoginFormListener();
        pageListener = new MainPageListener();
    }

    public String getUsernameTxt() {
        return usernameTxt.getText();
    }

    public String getPasswordTxt() {
        return passwordTxt.getText();
    }

    public void logIn(){
        AccessFormEvent formEvent = new AccessFormEvent(
                this,
                getUsernameTxt(),
                getPasswordTxt()
        );

        try {
            loginFormListener.eventOccurred(formEvent);
            pageListener.listen("login");
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }

    public void signUp(){
        AccessFormEvent formEvent = new AccessFormEvent(
                this,
                getUsernameTxt(),
                getPasswordTxt()
        );

        try {
            registrationFormListener.eventOccurred(formEvent);
            pageListener.listen("login");
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
}
