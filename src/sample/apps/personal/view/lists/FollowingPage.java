package sample.apps.personal.view.lists;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class FollowingPage extends Page {
    public FollowingPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FollowingPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
