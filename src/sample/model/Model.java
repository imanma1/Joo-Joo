package sample.model;

import sample.config.DBConfig;

import java.io.*;
import java.time.LocalDateTime;

public class Model {
    private static int lastId;

    private final int id;
    private LocalDateTime createAt;

    public Model(){
        this.id = lastId;
        lastId++;
        this.createAt = LocalDateTime.now();
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(new File(new DBConfig().getLastID())));
            printStream.println(lastId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public static void setLastId(int lastId) {
        Model.lastId = lastId;
    }
}
