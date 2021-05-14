package sample.apps.messages.view;

import sample.apps.messages.model.Group;
import sample.apps.messages.view.add.AddMemberPage;
import sample.listener.GroupListener;
import sample.view.MainPage;

public class MainPageToGroupMessagesPageListener implements GroupListener {
    private final MainPage mainPage;

    public MainPageToGroupMessagesPageListener() {
        mainPage = MainPage.getInstance();
    }

    @Override
    public void listen(Group group, String command) {
        if (command.equals("add")){
            AddMemberPage addMemberPage = new AddMemberPage();
            addMemberPage.getController().setGroup(group);
            mainPage.setPage(addMemberPage);
        }
    }
}
