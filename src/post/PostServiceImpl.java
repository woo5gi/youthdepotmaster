package post;

import java.util.ArrayList;
import java.util.List;

import vo.Post;

public class PostServiceImpl implements PostService {
	
	PostDao dao = new PostDaoOracle();

	@Override
	public ArrayList<Post> boardList(int brd_id, int realPage) {
		return dao.postList(brd_id, realPage);
	}

	@Override
	public Post getPostMenu(int brd_id, int post_id) {
		return dao.postMenu(brd_id ,post_id);
	}

	@Override
	public void deletePost(int post_id) {
		
	}

	@Override
	public void updatePost(Post post) {
		dao.updatePost(post);
		
	}

	@Override
	public void wirtePost(Post post) {
		dao.insertPost(post);
	}

	@Override
	public int findCount(int brd_id) {
		return dao.selectCount(brd_id);
	}

	@Override
	public ArrayList<Post> findAll(String mem_nickName, String post_title, String post_content) {
		return dao.searchAll(mem_nickName, post_title, post_content);
	}
	
	@Override
	public ArrayList<Post> findTitle(String post_title) {
		return dao.searchTitle(post_title);
	}

	@Override
	public ArrayList<Post> findWriter(String mem_nickName) {
		return dao.searchWriter(mem_nickName);
	}

	@Override
	public ArrayList<Post> findContent(String post_content) {
		return dao.searchContent(post_content);
	}

	@Override
	public ArrayList<Post> postList(int brd_id) {
		return dao.getPostList(brd_id);
	}
}
