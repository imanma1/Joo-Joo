package sample.listener;

import sample.apps.notifications.model.Request;

import java.util.LinkedList;

public interface CollectRequestsListener {
    LinkedList<Request> collectRequests();
}
