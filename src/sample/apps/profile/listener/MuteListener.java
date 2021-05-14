package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.controller.ProfileController;
import sample.listener.UserListener;

public class MuteListener implements UserListener {
    ProfileController profileController;

    public MuteListener(){
        profileController = new ProfileController();
    }

    @Override
    public void listen(User user, String command) {
        if (command.equals("Mute")){
            if (profileController.checkIfUserMutedThisAccount(user)){
                profileController.unmute(user);
            } else {
                profileController.mute(user);
            }
        }
    }
}
