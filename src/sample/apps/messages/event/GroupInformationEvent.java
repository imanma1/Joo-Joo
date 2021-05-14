package sample.apps.messages.event;

import sample.apps.authentication.model.User;
import sample.apps.messages.model.Message;

import java.util.EventObject;
import java.util.LinkedList;

public class GroupInformationEvent extends EventObject {
    private final String groupName;
    private final LinkedList<User> members;
    private final LinkedList<Message> messages;
    private final boolean isSeen;

    public GroupInformationEvent(Object source, String groupName, LinkedList<User> members, LinkedList<Message> messages, boolean isSeen) {
        super(source);
        this.groupName = groupName;
        this.members = members;
        this.messages = messages;
        this.isSeen = isSeen;
    }

    public String getGroupName() {
        return groupName;
    }

    public LinkedList<User> getMembers() {
        return members;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public boolean isSeen() {
        return isSeen;
    }
}
