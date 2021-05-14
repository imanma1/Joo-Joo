package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.controller.ProfileController;
import sample.listener.IsProfileButtonPressedListener;

public class IsMuteButtonPressedListener implements IsProfileButtonPressedListener {
    private final ProfileController profileController;

    public IsMuteButtonPressedListener(){
        profileController = new ProfileController();
    }

    @Override
    public boolean isItPressed(User user) {
        return profileController.checkIfUserMutedThisAccount(user);
    }

    public String getTextOfLabel(User user){
        if (isItPressed(user)){
            return "Unmute";
        } else {
            return "Mute";
        }
    }
}
