package sample.apps.post.view.share;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class SharePage extends Page {
    public SharePage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SharePage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
