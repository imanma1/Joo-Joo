package sample.apps.personal.view.info;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class InfoPage extends Page {
    public InfoPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfoPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
