package sample.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sample.apps.authentication.model.User;

public abstract class Page {
    protected Pane pane;
    protected PageController controller;

    public Pane getPane() {
        return pane;
    }

    public PageController getController() {
        return controller;
    }
}
