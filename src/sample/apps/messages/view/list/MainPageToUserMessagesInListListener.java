package sample.apps.messages.view.list;

import sample.apps.messages.model.Messages;
import sample.apps.messages.view.UserMessagesPage;
import sample.listener.MessagesListener;
import sample.view.MainPage;

public class MainPageToUserMessagesInListListener implements MessagesListener {
    private MainPage mainPage;

    public MainPageToUserMessagesInListListener() {
        mainPage = MainPage.getInstance();
    }

    @Override
    public void listen(Messages messages, String command) {
        if (command.equals("messages")){
            UserMessagesPage messagesPage = new UserMessagesPage();
            messagesPage.getController().setMessages(messages);
            mainPage.setPage(messagesPage);
        }
    }
}
