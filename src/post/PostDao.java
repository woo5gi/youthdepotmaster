package post;

import java.util.ArrayList;

import vo.Post;

public interface PostDao {
	ArrayList<Post> postList(int brd_id);
	Post postMenu(int post_id);
}
