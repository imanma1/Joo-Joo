package sample.apps.personal.view.lists;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class FollowersPage extends Page {
    public FollowersPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FollowersPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
