package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.apps.post.model.Comment;
import sample.apps.post.model.Post;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class CommentDB implements DBSet<Comment>{
    private File DBDirectory;

    public CommentDB() {
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBCommentsPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Comment get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Comment comment = gson.fromJson(bufferedReader, Comment.class);
            bufferedReader.close();
            return comment;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Comment> all() {
        try {
            LinkedList<Comment> comments = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Comment comment = gson.fromJson(bufferedReader, Comment.class);
                comments.add(comment);
                bufferedReader.close();
            }
            return comments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Comment> commentsOfPost(Post post){
        LinkedList<Comment> comments = new LinkedList<>();
        for (int commentID : post.getComments()) {
            comments.add(get(commentID));
        }
        return comments;
    }

    public LinkedList<Comment> allCommentsOfUser(User user){
        LinkedList<Comment> comments = new LinkedList<>();
        LinkedList<Comment> allComments = all();
        for (Comment comment : allComments) {
            if (comment.getSender() == user.getId())
                comments.add(comment);
        }
        return comments;
    }

    @Override
    public void add(Comment comment) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, comment.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(comment);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Comment comment) {
        File file = new File(DBDirectory, comment.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Comment comment) {
        remove(comment);
        add(comment);
    }
}
