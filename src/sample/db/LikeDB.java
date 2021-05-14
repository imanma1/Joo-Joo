package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.apps.post.model.Like;
import sample.apps.post.model.Post;
import sample.apps.post.model.Tweet;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class LikeDB implements DBSet<Like> {
    private File DBDirectory;

    public LikeDB() {
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBLikesPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Like get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Like like = gson.fromJson(bufferedReader, Like.class);
            bufferedReader.close();
            return like;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Like> all() {
        try {
            LinkedList<Like> likes = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Like like = gson.fromJson(bufferedReader, Like.class);
                likes.add(like);
                bufferedReader.close();
            }
            return likes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Like> likesOfPost(Post post){
        LinkedList<Like> likes = new LinkedList<>();
        LinkedList<Like> allLikes = all();
        for (Like like : allLikes) {
            if (like.getPost() == post.getId()) likes.add(like);
        }
        return likes;
    }

    public LinkedList<Like> likesOfUser(User user){
        LinkedList<Like> likes = new LinkedList<>();
        LinkedList<Like> allLikes = all();
        for (Like like : allLikes) {
            if (like.getLiker() == user.getId()) likes.add(like);
        }
        return likes;
    }

    @Override
    public void add(Like like) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, like.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(like);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Like like) {
        File file = new File(DBDirectory, like.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Like like) {
        remove(like);
        add(like);
    }
}
