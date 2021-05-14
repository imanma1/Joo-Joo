package sample.apps.messages.view.list;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class UserInMessagesList extends Page {
    public UserInMessagesList(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserInMessagesList.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
