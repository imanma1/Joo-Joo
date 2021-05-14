package sample.listener;

import sample.apps.post.event.PostInformationEvent;
import sample.apps.post.model.Post;

public interface CollectPostInformationListener {
    PostInformationEvent collectPostInformation(Post post);
}
