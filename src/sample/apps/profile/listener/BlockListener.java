package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.controller.ProfileController;
import sample.listener.UserListener;

public class BlockListener implements UserListener {
    private final ProfileController profileController;

    public BlockListener(){
        profileController = new ProfileController();
    }

    @Override
    public void listen(User user, String command) {
        if (command.equals("Block")){
            if (profileController.checkIfUserBlockedThisAccount(user)){
                profileController.unblock(user);
            } else {
                profileController.block(user);
            }
        }
    }
}
