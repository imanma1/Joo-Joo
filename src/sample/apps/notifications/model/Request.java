package sample.apps.notifications.model;

import sample.model.Model;

public class Request extends Model {

    private final int receiver;
    private final int sender;

    public Request(int receiver, int sender) {
        super();
        this.receiver = receiver;
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public int getSender() {
        return sender;
    }
}
