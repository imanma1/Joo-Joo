package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.apps.messages.model.Messages;
import sample.apps.post.model.Like;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class MessagesDB implements DBSet<Messages>{
    private File DBDirectory;

    public MessagesDB(){
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBUserMessagesPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Messages get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Messages messages = gson.fromJson(bufferedReader, Messages.class);
            bufferedReader.close();
            return messages;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Messages getMessagesWithUser(User user1, User user2){
        for (int messagesID : user1.getMessages()) {
            if (get(messagesID).getUser2(user1.getId()) == user2.getId())
                return get(messagesID);
        }
        return null;
    }

    @Override
    public LinkedList<Messages> all() {
        try {
            LinkedList<Messages> userMessages = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Messages messages = gson.fromJson(bufferedReader, Messages.class);
                userMessages.add(messages);
                bufferedReader.close();
            }
            return userMessages;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Messages> allMessagesOfUser(User user){
        LinkedList<Messages> messages = new LinkedList<>();
        for (int messagesID : user.getMessages()) {
            messages.add(get(messagesID));
        }
        return messages;
    }

    @Override
    public void add(Messages messages) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, messages.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(messages);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Messages messages) {
        File file = new File(DBDirectory, messages.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Messages messages) {
        remove(messages);
        add(messages);
    }
}
