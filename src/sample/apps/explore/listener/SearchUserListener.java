package sample.apps.explore.listener;

import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.model.User;
import sample.apps.explore.controller.ExploreController;
import sample.listener.SearchUsernameListener;
import sample.listener.StringListener;

public class SearchUserListener implements SearchUsernameListener {
    private final ExploreController controller;

    public SearchUserListener(){
        controller = new ExploreController();
    }


    @Override
    public User search(String username) throws UserDoesNotExistException {
        return controller.returnUser(username);
    }
}
