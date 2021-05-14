package sample.apps.messages.view.list;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class GroupInMessagesList extends Page {
    public GroupInMessagesList(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GroupInMessagesList.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
