package sample.apps.notifications.controller;

import sample.apps.authentication.model.User;
import sample.apps.notifications.model.Request;
import sample.controller.Controller;


import java.util.LinkedList;

public class NotificationsController extends Controller {

    public String collectRequestInformation(Request request){
        return context.getUsers().get(request.getSender()).getUsername() +
                " requested to follow you";
    }

    public void acceptRequest(Request request){
        User user = context.getUsers().get(request.getSender());
        this.user.getFollowers().add(user.getId());
        user.getFollowing().add(this.user.getId());
        user.getNotifications().add(this.user.getUsername()
        + " accepted your request");
        this.user.getNotifications().add(user.getUsername()
        + " started following you");
        deleteRequest(request);
        context.getUsers().update(user);
        context.getUsers().update(this.user);
    }

    public void rejectRequestAndDontInform(Request request){
        deleteRequest(request);
        context.getUsers().update(this.user);
    }

    public void rejectRequestAndInform(Request request){
        User user = context.getUsers().get(request.getSender());
        user.getNotifications().add(this.user.getUsername()
                + " rejected your request");
        deleteRequest(request);
        context.getUsers().update(user);
        context.getUsers().update(this.user);
    }

    private void deleteRequest(Request request){
        this.user.getRequests().remove(Integer.valueOf(request.getId()));
        context.getRequests().remove(request);
    }

    public LinkedList<String> collectNotifications(){
        return user.getNotifications();
    }

    public LinkedList<Request> collectRequests(){
        LinkedList<Request> requests = new LinkedList<>();
        for (Request request : context.getRequests().allReceivedRequests(user)) {
            if (!context.getUsers().get(request.getSender()).isInactive())
                requests.add(request);
        }
        return requests;
    }
}
