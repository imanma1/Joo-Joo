package sample.apps.post.exception;

public class AccountIsPrivateException extends Exception {
    public AccountIsPrivateException(){
        super("this account is private. you can't retweet");
    }
}
