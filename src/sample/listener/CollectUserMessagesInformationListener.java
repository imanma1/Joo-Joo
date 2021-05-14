package sample.listener;

import sample.apps.messages.event.MessagesInformationEvent;
import sample.apps.messages.model.Messages;

public interface CollectUserMessagesInformationListener {
    MessagesInformationEvent collectInformation(Messages messages);
}
