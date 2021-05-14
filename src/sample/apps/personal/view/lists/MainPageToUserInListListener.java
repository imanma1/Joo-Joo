package sample.apps.personal.view.lists;

import sample.apps.authentication.model.User;
import sample.apps.messages.view.UserMessagesPage;
import sample.apps.personal.view.PersonalPage;
import sample.apps.profile.view.ProfilePage;
import sample.controller.Controller;
import sample.listener.UserListener;
import sample.view.MainPage;

public class MainPageToUserInListListener implements UserListener {
    private final MainPage mainPage;

    public MainPageToUserInListListener() {
        mainPage = MainPage.getInstance();
    }

    @Override
    public void listen(User user, String command) {
        if (command.equals("profile")){
            if (user.getId() == Controller.getUser().getId()){
                PersonalPage personalPage = new PersonalPage();
                mainPage.setPage(personalPage);
            } else {
                ProfilePage profilePage = new ProfilePage();
                profilePage.getController().setUser(user);
                mainPage.setPage(profilePage);
            }
        } else if (command.equals("messages")){
            UserMessagesPage messagesPage = new UserMessagesPage();
            messagesPage.getController().setUser(user);
            mainPage.setPage(messagesPage);
        }
    }
}
