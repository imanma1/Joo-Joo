package sample.apps.image.listener;

import javafx.scene.image.Image;
import sample.apps.image.view.ImagePage;
import sample.view.MainPage;

public class NewImagePageListener {

    private MainPage mainPage;

    public NewImagePageListener() {
        mainPage = MainPage.getInstance();
    }

    public void newImagePage(Image image){
        ImagePage imagePage = new ImagePage();
        imagePage.getController().setImage(image);
        mainPage.setPage(imagePage);
    }
}
