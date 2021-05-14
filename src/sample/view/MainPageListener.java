package sample.view;

import sample.apps.authentication.view.AccessPage;
import sample.apps.explore.view.ExplorePage;
import sample.apps.notifications.view.NotificationsPage;
import sample.apps.notifications.view.request.RequestsPage;
import sample.apps.personal.view.PersonalPage;
import sample.apps.personal.view.edit.EditProfilePage;
import sample.apps.personal.view.info.InfoPage;
import sample.apps.personal.view.lists.FollowersPage;
import sample.apps.personal.view.lists.FollowingPage;
import sample.apps.settings.view.SettingsPage;
import sample.apps.timeline.view.TimelinePage;
import sample.listener.StringListener;

public class MainPageListener implements StringListener {
    private MainPage mainPage;

    public MainPageListener() {
        mainPage = MainPage.getInstance();
    }

    @Override
    public void listen(String command) {
        if (command.equals("login")){
//          mainPage.setMenu(new MenuSideBar());
//          mainPage.getPane().setLeft(mainPage.getMenu().getPane());
            mainPage.getPane().getLeft().setVisible(true);
          listen("home");
        } else if (command.equals("access")){
//            mainPage.getPane().setLeft(null);
            mainPage.getPane().getLeft().setVisible(false);
            mainPage.setCurrentPage(new AccessPage());
            mainPage.getPane().setCenter(mainPage.getCurrentPage().getPane());
        } else if (command.equals("back")){
            mainPage.back();
        } else if (command.equals("home")){
            mainPage.setPage(new TimelinePage());
        } else if (command.equals("explore")){
            mainPage.setPage(new ExplorePage());
        } else if (command.equals("notifications")){
            mainPage.setPage(new NotificationsPage());
        } else if (command.equals("messages")){
//            mainPage.setPage(new MessagesP);
        } else if (command.equals("profile")){
            mainPage.setPage(new PersonalPage());
        } else if (command.equals("settings")){
            mainPage.setPage(new SettingsPage());
        } else if (command.equals("requests")){
            mainPage.setPage(new RequestsPage());
        } else if (command.equals("edit")){
            mainPage.setPage(new EditProfilePage());
        } else if (command.equals("info")){
            mainPage.setPage(new InfoPage());
        } else if (command.equals("followers")){
            mainPage.setPage(new FollowersPage());
        } else if (command.equals("following")){
            mainPage.setPage(new FollowingPage());
        } else if (command.equals("logout")){
            mainPage.getStack().clear();
            listen("access");
        }
    }
}
