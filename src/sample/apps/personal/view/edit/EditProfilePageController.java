package sample.apps.personal.view.edit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.apps.image.controller.ImageController;
import sample.apps.personal.event.FurtherPersonalInformationEvent;
import sample.apps.personal.listener.ChangeInformationListener;
import sample.view.PageController;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditProfilePageController extends PageController implements Initializable {
    ChangeInformationListener listener = new ChangeInformationListener();

    String imagePath = "";

    @FXML
    TextField usernameTxt;

    @FXML
    TextField nameTxt;

    @FXML
    TextField emailTxt;

    @FXML
    TextField bioTxt;

    @FXML
    DatePicker birthDate;

    @FXML
    Label error;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setText("");
    }

    private String getUsername(){
        return usernameTxt.getText();
    }

    private String getName(){
        return nameTxt.getText();
    }

    private String getBio(){
        return bioTxt.getText();
    }

    private String getEmail(){
        return emailTxt.getText();
    }

    private LocalDate getBirth(){
        return birthDate.getValue();
    }

    public void save(){
        FurtherPersonalInformationEvent event = new FurtherPersonalInformationEvent(
                this,
                getUsername(),
                getName(),
                getEmail(),
                getBio(),
                getBirth(),
                imagePath
        );

        try {
            listener.changeInformation(event);
            error.setText("");
        } catch (UserAlreadyExistException e){
            error.setText(e.getMessage());
        }
    }

    public void uploadImage(){
        imagePath = new ImageController().uploadImage();
    }

}
