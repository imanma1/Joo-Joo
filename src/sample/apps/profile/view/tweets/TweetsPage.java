package sample.apps.profile.view.tweets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class TweetsPage extends Page {
    public TweetsPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TweetsPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
