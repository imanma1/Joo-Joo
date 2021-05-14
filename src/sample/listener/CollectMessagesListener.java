package sample.listener;

import sample.apps.messages.model.Messages;

import java.util.LinkedList;

public interface CollectMessagesListener {
    LinkedList<Messages> collectMessages();
}
