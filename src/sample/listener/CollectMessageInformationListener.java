package sample.listener;

import sample.apps.messages.event.MessageInformationEvent;
import sample.apps.messages.model.Message;

public interface CollectMessageInformationListener {
    MessageInformationEvent collectInformation(Message message);
}
