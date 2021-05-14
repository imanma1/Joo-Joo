package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.post.model.Post;
import sample.apps.profile.controller.ProfileController;
import sample.listener.IsProfileButtonPressedListener;

public class IsFollowButtonPressedListener implements IsProfileButtonPressedListener {
    private final ProfileController profileController;

    public IsFollowButtonPressedListener() {
        this.profileController = new ProfileController();
    }

    @Override
    public boolean isItPressed(User user) {
        return profileController.checkIfUserFollowedThisAccount(user);
    }

    public String getTextOfLabel(User user){
        if (profileController.checkIfUserRequestedThisAccount(user)){
            return "Requested";
        } else if (isItPressed(user)){
            return "Unfollow";
        } else {
            return "Follow";
        }
    }
}
