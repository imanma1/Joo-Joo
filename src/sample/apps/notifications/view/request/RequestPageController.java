package sample.apps.notifications.view.request;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.apps.notifications.listener.AcceptOrRejectRequestListener;
import sample.apps.notifications.listener.RequestInformationListener;
import sample.apps.notifications.model.Request;
import sample.view.MainPageListener;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestPageController extends PageController implements Initializable {
    RequestInformationListener requestInformationListener;
    AcceptOrRejectRequestListener listener;
    MainPageListener pageListener;

    @FXML
    Label requestLabel;

    @FXML
    Button acceptBtn;

    @FXML
    Button rejectAndDontInform;

    @FXML
    Button rejectAndInformBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requestInformationListener = new RequestInformationListener();
        listener = new AcceptOrRejectRequestListener();
        pageListener = new MainPageListener();
    }

    @Override
    public void setRequest(Request request) {
        setInformation(request);
    }

    private void setInformation(Request request){
        requestLabel.setText(
                requestInformationListener.collectInformation(request)
        );
    }

    public void accept(){
        listener.listen(request, "Accept");
        refresh();
    }

    public void rejectAndInform(){
        listener.listen(request, "Reject and inform");
        refresh();
    }

    public void rejectAndDontInform(){
        listener.listen(request, "Reject and dont inform");
        refresh();
    }

    private void refresh(){
        pageListener.listen("back");
        pageListener.listen("requests");
    }
}
