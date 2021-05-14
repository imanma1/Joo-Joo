package sample.apps.personal.listener;

import javafx.fxml.Initializable;
import sample.apps.personal.controller.PersonalController;
import sample.apps.personal.event.FurtherPersonalInformationEvent;
import sample.listener.CollectFurtherPersonalInformationListener;

import java.net.URL;
import java.util.ResourceBundle;

public class FurtherPersonalInformationListener implements CollectFurtherPersonalInformationListener {
    private final PersonalController controller;

    public FurtherPersonalInformationListener(){
        controller = new PersonalController();
    }

    @Override
    public FurtherPersonalInformationEvent collectInformation() {
        return controller.collectFurtherInformation();
    }
}
