package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.PostListener;

public class ReportListener implements PostListener {
    private final TweetController tweetController;

    public ReportListener(){
        tweetController = new TweetController();
    }

    @Override
    public void listen(Post post, String command) {
        if (command.equals("Report")){
            if (!new IsReportButtonPressedListener().isItPressed(post))
                tweetController.report(post);
        }
    }
}
