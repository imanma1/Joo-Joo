package sample.apps.notifications.listener;

import sample.apps.notifications.controller.NotificationsController;
import sample.apps.notifications.model.Request;
import sample.listener.CollectRequestsListener;

import java.util.LinkedList;

public class CollectRequestListener implements CollectRequestsListener {
    private final NotificationsController controller;

    public CollectRequestListener(){
        controller = new NotificationsController();
    }

    @Override
    public LinkedList<Request> collectRequests() {
        return controller.collectRequests();
    }
}
