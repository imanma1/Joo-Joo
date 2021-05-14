package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.controller.ProfileController;
import sample.listener.UserListener;

public class FollowListener implements UserListener {
    private final ProfileController profileController;

    public FollowListener(){
        profileController = new ProfileController();
    }

    @Override
    public void listen(User user, String command) {
        if (command.equals("Follow")){
            if (profileController.checkIfUserFollowedThisAccount(user)){
                profileController.unFollow(user);
            } else {
                if (profileController.checkIfAccountIsPublic(user)){
                    profileController.follow(user);
                } else {
                    if (profileController.checkIfUserRequestedThisAccount(user)){
                        profileController.undoRequest(user);
                    } else {
                        profileController.request(user);
                    }
                }
            }
        }
    }
}
