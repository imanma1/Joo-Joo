package sample.apps.personal.view.info;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.apps.personal.event.FurtherPersonalInformationEvent;
import sample.apps.personal.listener.FurtherPersonalInformationListener;
import sample.view.PageController;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoPageController extends PageController implements Initializable {
    private FurtherPersonalInformationListener listener;
    @FXML
    Label usernameLabel;

    @FXML
    Label nameLabel;

    @FXML
    Label emailLabel;

    @FXML
    Label bioLabel;

    @FXML
    Label birthLabel;

    @FXML
    ImageView avatar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listener = new FurtherPersonalInformationListener();
        birthLabel.setText("");
        setInformation();
    }

    private void setInformation(){
        FurtherPersonalInformationEvent data = listener.collectInformation();
        setUsername(data);
        setName(data);
        setEmail(data);
        setBio(data);
        setBirth(data);
        setAvatar(data);
    }

    private void setUsername(FurtherPersonalInformationEvent data){
        usernameLabel.setText(data.getUsername());
    }

    private void setName(FurtherPersonalInformationEvent data){
        nameLabel.setText(data.getName());
    }

    private void setEmail(FurtherPersonalInformationEvent data){
        emailLabel.setText(data.getEmail());
    }

    private void setBio(FurtherPersonalInformationEvent data){
        bioLabel.setText(data.getBio());
    }

    private void setBirth(FurtherPersonalInformationEvent data){
        try {
            birthLabel.setText(data.getBirth().toString());
        } catch (NullPointerException e){ }
    }

    private void setAvatar(FurtherPersonalInformationEvent data){
        if (!data.getImagePath().isEmpty()){
            try {
                avatar.setImage(new Image(new FileInputStream(new File(data.getImagePath()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
