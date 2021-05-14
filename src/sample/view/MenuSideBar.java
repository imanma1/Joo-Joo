package sample.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.apps.authentication.listener.RegistrationFormListener;

import java.io.IOException;

public class MenuSideBar extends Page {

    public MenuSideBar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuSideBar.fxml"));
            Parent root = fxmlLoader.load();
            this.pane = new AnchorPane(root);
            this.controller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
