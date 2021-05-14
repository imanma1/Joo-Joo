package sample.apps.notifications.listener;

import sample.apps.notifications.controller.NotificationsController;
import sample.apps.notifications.model.Request;
import sample.listener.CollectRequestInformationListener;

public class RequestInformationListener implements CollectRequestInformationListener {
    private final NotificationsController controller;

    public RequestInformationListener(){
        controller = new NotificationsController();
    }

    @Override
    public String collectInformation(Request request) {
        return controller.collectRequestInformation(request);
    }
}
