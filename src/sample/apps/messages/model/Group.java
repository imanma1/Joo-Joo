package sample.apps.messages.model;

import sample.apps.authentication.model.User;
import sample.model.Model;

import java.util.*;

public class Group extends Model {

    private final String name;
    private final LinkedList<Integer> members = new LinkedList<>();
    private final LinkedList<Integer> messages = new LinkedList<>();
    private final Map<Integer, Boolean> isSeen = new HashMap<>();

    public Group(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Integer> getMembers() {
        return members;
    }

    public LinkedList<Integer> getMessages() {
        return messages;
    }

    public Map<Integer, Boolean> getIsSeen() {
        return isSeen;
    }

    public void addMember(User user){
        getMembers().add(user.getId());
        isSeen.put(user.getId(), false);
    }
}
