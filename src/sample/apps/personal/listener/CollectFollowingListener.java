package sample.apps.personal.listener;

import sample.apps.authentication.model.User;
import sample.apps.personal.controller.PersonalController;
import sample.listener.CollectUsersListener;

import java.util.LinkedList;

public class CollectFollowingListener implements CollectUsersListener {
    private final PersonalController personalController;

    public CollectFollowingListener(){
        personalController = new PersonalController();
    }

    @Override
    public LinkedList<User> collectUsers() {
        return personalController.collectFollowing();
    }
}
