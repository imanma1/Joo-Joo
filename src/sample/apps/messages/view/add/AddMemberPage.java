package sample.apps.messages.view.add;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class AddMemberPage extends Page {
    public AddMemberPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMemberPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
