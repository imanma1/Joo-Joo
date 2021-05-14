package sample.apps.messages.model;

import sample.model.Model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Messages extends Model {
    private final int user1;
    private final int user2;
    private final LinkedList<Integer> messages = new LinkedList<>();
    private final Map<Integer, Boolean> isSeen = new HashMap<>();

    public Messages(int user1, int user2) {
        super();
        this.user1 = user1;
        this.user2 = user2;
        isSeen.put(user1, true);
        isSeen.put(user2, true);
    }

    public int getUser2(int user1){
        if(user1 == this.user1) return user2;
        else return user1;
    }

    public LinkedList<Integer> getMessages() {
        return messages;
    }

    public Map<Integer, Boolean> getIsSeen() {
        return isSeen;
    }
}
