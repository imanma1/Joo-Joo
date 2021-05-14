package sample.apps.notifications.listener;

import sample.apps.notifications.controller.NotificationsController;
import sample.listener.CollectNotificationsListener;

import java.util.LinkedList;

public class CollectNotificationListener implements CollectNotificationsListener {
    private final NotificationsController controller;

    public CollectNotificationListener(){
        controller = new NotificationsController();
    }

    @Override
    public LinkedList<String> collectNotifications() {
        return controller.collectNotifications();
    }
}
