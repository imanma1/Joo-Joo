package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.controller.ProfileController;
import sample.listener.IsProfileButtonPressedListener;

public class IsBlockButtonPressedListener implements IsProfileButtonPressedListener {
    private final ProfileController profileController;

    public IsBlockButtonPressedListener(){
        profileController = new ProfileController();
    }

    @Override
    public boolean isItPressed(User user) {
        return profileController.checkIfUserBlockedThisAccount(user);
    }

    public String getTextOfLabel(User user){
        if (isItPressed(user)){
            return "Unblock";
        } else {
            return "Block";
        }
    }
}
