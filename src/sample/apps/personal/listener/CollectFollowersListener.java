package sample.apps.personal.listener;

import sample.apps.authentication.model.User;
import sample.apps.personal.controller.PersonalController;
import sample.listener.CollectUsersListener;

import java.util.LinkedList;

public class CollectFollowersListener implements CollectUsersListener {
    private final PersonalController personalController;

    public CollectFollowersListener(){
        personalController = new PersonalController();
    }

    @Override
    public LinkedList<User> collectUsers() {
        return personalController.collectFollowers();
    }
}
