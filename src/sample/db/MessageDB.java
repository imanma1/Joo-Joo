package sample.db;

import com.google.gson.Gson;
import sample.apps.messages.model.Message;
import sample.apps.post.model.Tweet;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class MessageDB implements DBSet<Message> {
    private File DBDirectory;

    public MessageDB() {
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBMessagesPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Message get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Message message = gson.fromJson(bufferedReader, Message.class);
            bufferedReader.close();
            return message;
        } catch (IOException e) {
//            e.printStackTrace(); ///file not found
        }
        return null;
    }

    @Override
    public LinkedList<Message> all() {
        try {
            LinkedList<Message> messages = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Message message = gson.fromJson(bufferedReader, Message.class);
                messages.add(message);
                bufferedReader.close();
            }
            return messages;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Message message) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, message.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(message);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Message message) {
        File file = new File(DBDirectory, message.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Message message) {
        remove(message);
        add(message);
    }
}
