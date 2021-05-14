package sample.apps.notifications.view.request;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import sample.apps.notifications.listener.CollectRequestListener;
import sample.apps.notifications.model.Request;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class RequestsPageController extends PageController implements Initializable {
    CollectRequestListener collectRequestListener;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collectRequestListener = new CollectRequestListener();
        setInformation();
    }

    private void setInformation(){
        LinkedList<Request> requests = collectRequestListener.collectRequests();
        for (Request request : requests) {
            RequestPage requestPage = new RequestPage();
            requestPage.getController().setRequest(request);
            box.getChildren().add(requestPage.getPane());
        }
    }
}
