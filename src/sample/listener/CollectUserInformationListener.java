package sample.listener;

import sample.apps.authentication.model.User;
import sample.apps.profile.event.UserInformationEvent;

public interface CollectUserInformationListener {
    UserInformationEvent collectInformation(User user);
}
