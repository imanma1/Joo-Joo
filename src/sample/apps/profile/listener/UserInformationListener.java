package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.controller.ProfileController;
import sample.apps.profile.event.UserInformationEvent;
import sample.listener.CollectUserInformationListener;
import sample.listener.CollectUsersListener;

import java.util.LinkedList;

public class UserInformationListener implements CollectUserInformationListener {
    private final ProfileController profileController;

    public UserInformationListener(){
        profileController = new ProfileController();
    }


    @Override
    public UserInformationEvent collectInformation(User user) {
        return profileController.collectInformation(user);
    }
}
