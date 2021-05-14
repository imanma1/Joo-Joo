package sample.apps.notifications.listener;

import sample.apps.notifications.controller.NotificationsController;
import sample.apps.notifications.model.Request;
import sample.listener.RequestListener;
import sample.listener.StringListener;

public class AcceptOrRejectRequestListener implements RequestListener {
    private final NotificationsController controller;

    public AcceptOrRejectRequestListener(){
        controller = new NotificationsController();
    }


    @Override
    public void listen(Request request, String command) {
        switch (command) {
            case "Accept" -> controller.acceptRequest(request);
            case "Reject and inform" -> controller.rejectRequestAndInform(request);
            case "Reject and dont inform" -> controller.rejectRequestAndDontInform(request);
        }
    }
}
