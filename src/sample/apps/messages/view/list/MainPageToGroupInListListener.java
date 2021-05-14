package sample.apps.messages.view.list;

import sample.apps.messages.model.Group;
import sample.apps.messages.view.GroupMessagesPage;
import sample.listener.GroupListener;
import sample.view.MainPage;

public class MainPageToGroupInListListener implements GroupListener {
    private MainPage mainPage;

    public MainPageToGroupInListListener() {
        mainPage = MainPage.getInstance();
    }


    @Override
    public void listen(Group group, String command) {
        if (command.equals("messages")){
            GroupMessagesPage messagesPage = new GroupMessagesPage();
            messagesPage.getController().setGroup(group);
            mainPage.setPage(messagesPage);
        }
    }
}
