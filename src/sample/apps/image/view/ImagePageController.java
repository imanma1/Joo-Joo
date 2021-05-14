package sample.apps.image.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.view.PageController;


import java.net.URL;
import java.util.ResourceBundle;

public class ImagePageController extends PageController implements Initializable {

    @FXML
    ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    public void setImage(Image image) {
        super.setImage(image);
        setInformation();
    }

    private void setInformation(){
        imageView.setImage(image);
    }
}
