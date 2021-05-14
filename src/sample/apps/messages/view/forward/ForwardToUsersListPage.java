package sample.apps.messages.view.forward;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class ForwardToUsersListPage extends Page {
    public ForwardToUsersListPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ForwardToUsersListPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
