package sample.apps.settings.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.view.Page;

import java.io.IOException;

public class SettingsPage extends Page {
    public SettingsPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsPage.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
