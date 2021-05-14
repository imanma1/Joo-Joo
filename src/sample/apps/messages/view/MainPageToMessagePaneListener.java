package sample.apps.messages.view;

import sample.apps.messages.model.Message;
import sample.apps.messages.view.edit.EditMessagePage;
import sample.apps.messages.view.forward.ForwardPage;
import sample.listener.MessageListener;
import sample.view.MainPage;

public class MainPageToMessagePaneListener implements MessageListener {
    private final MainPage mainPage;

    public MainPageToMessagePaneListener() {
        mainPage = MainPage.getInstance();
    }

    @Override
    public void listen(Message message, String command) {
        if (command.equals("forward")){
            ForwardPage forwardPage = new ForwardPage();
            forwardPage.getController().setMessage(message);
            mainPage.setPage(forwardPage);
        } else if (command.equals("edit")){
            EditMessagePage editPage = new EditMessagePage();
            editPage.getController().setMessage(message);
            mainPage.setPage(editPage);
        }
    }
}
