package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.apps.notifications.model.Request;
import sample.apps.post.model.Like;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class RequestDB implements DBSet<Request> {
    private File DBDirectory;

    public RequestDB(){
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBRequestPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Request get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Request request = gson.fromJson(bufferedReader, Request.class);
            bufferedReader.close();
            return request;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Request getRequestOfUserToThisAccount(User receiver, User sender){
        LinkedList<Request> requests = allReceivedRequests(receiver);
        for (Request request : requests) {
            if (sender.getId() == request.getSender()) return request;
        }
        return null;
    }

    @Override
    public LinkedList<Request> all() {
        try {
            LinkedList<Request> requests = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Request request = gson.fromJson(bufferedReader, Request.class);
                requests.add(request);
                bufferedReader.close();
            }
            return requests;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Request> allReceivedRequests(User user){
        LinkedList<Request> requests = new LinkedList<>();
        for (int requestID : user.getRequests()) {
            requests.add(get(requestID));
        }
        return requests;
    }

    public LinkedList<Request> allSentRequest(User user){
        LinkedList<Request> all = all();
        LinkedList<Request> requests = new LinkedList<>();
        for (Request request : all) {
            if (request.getSender() == user.getId()) requests.add(request);
        }
        return requests;
    }

    @Override
    public void add(Request request) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, request.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(request);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Request request) {
        File file = new File(DBDirectory, request.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Request request) {
        remove(request);
        add(request);
    }
}
