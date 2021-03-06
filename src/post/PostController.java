package post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import projcet.RenamePolicy;
import vo.Board;
import vo.PageBean;
import vo.Post;

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PostService service = new PostServiceImpl();
		ArrayList<Post> data;
		String type = request.getParameter("type");
		String searchText = request.getParameter("searchText");
		String forwardURL = "";
		int brd_id = 0; //게시판 번호 
		int post_id = 0; //게시물 번호
		int realPage = 1; // 처음 게시판을 클릭했을 경우 1페이지 보기
		Post p = null;
		Board b = null;
		if(type.equals("boardList")) {
			brd_id = Integer.parseInt(request.getParameter("brd_id")); //헤더에서 게시판 클릭에 따라서 brd_id 값 
			String page = request.getParameter("page"); //페이지 번호 클릭할 경우 페이지 값 
			int totalCount = service.findCount(brd_id); //해당 게시판 총 게시물 (삭제한 게시물은 flag로 제외 해서)
			int cntPerPage = 10; //1페이지 별 10건씩 게시글을 보여준다.
			if(page != null) { //처음 게시판 클릭은 queryString에 page값이 null, 페이지 숫자 클릭할 경우 페이지 변수 생성
				realPage = Integer.parseInt(page);
			}
			data = service.boardList(brd_id, realPage); //게시판 번호와 페이지 번호에 따라서 해당 페이지의 게시물 data 값 저장
			int totalPage = (int) Math.ceil((double) totalCount / cntPerPage);
			int cntPerPageGroup = 5; // 페이지 그룹별 5페이지씩 보여준다.
			int startPage = ((int) ((realPage/cntPerPageGroup)+0.8))*cntPerPageGroup + 1; // 페이징 처리 번호 게시 하기 위해서
			System.out.println("startPage :" +startPage);
			System.out.println("totalPage :" +totalPage);
			int endPage = startPage + cntPerPageGroup - 1;
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			PageBean<Post> pb = new PageBean<>(); // 페이징 처리 위해서 Bean 값 저장
			pb.setCurrentPage(realPage);
			pb.setTotalPage(totalPage);
			pb.setList(data);
			pb.setStartPage(startPage);
			pb.setEndPage(endPage);
			pb.setTotalCount(totalCount);
			pb.setCntPerPage(cntPerPageGroup);
			request.setAttribute("data", data);
			request.setAttribute("pagebean", pb);
			System.out.println(realPage);
			forwardURL = "user/boards/boardlist.jsp"; // setAttribute 후 forward
		
		} else if(type.equals("boardView")) {
			p = new Post();
			brd_id = Integer.parseInt(request.getParameter("brd_id"));
			post_id = Integer.parseInt(request.getParameter("post_id"));
			p = service.getPostMenu(brd_id, post_id);
			System.out.println("p" + p);
			request.setAttribute("p", p);
			forwardURL = "user/boards/boardview.jsp";
		
		}
		else if (type.equals("boardupdate")) {
			b = new Board();
			p = new Post();
			b.setBrd_id(Integer.parseInt(request.getParameter("brd")));
			p.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			p.setAdmin_id(request.getParameter("id"));
			p.setPost_title(request.getParameter("title"));
			p.setPost_content(request.getParameter("content"));
			request.setAttribute("p", p);
			forwardURL = "user/boards/boardupdate.jsp";
		}
		else if (type.equals("boardupdateok")) {
			p = new Post();
			b = new Board();
			b.setBrd_id(Integer.parseInt(request.getParameter("brd")));
			p.setBoard_id(b);
			p.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			p.setAdmin_id(request.getParameter("name"));
			p.setPost_title(request.getParameter("title"));
			p.setPost_content(request.getParameter("content"));
			service.updatePost(p);
			forwardURL = "/PostController?type=boardView&id=" + request.getParameter("post_id") + "&brd_id="+ request.getParameter("brd");
		} 
		else if (type.equals("boardwrite")) {
			System.out.println("boardwrite 호출");
			HttpSession session = request.getSession();
			int mem_id = Integer.parseInt(session.getAttribute("mem_id").toString());
			System.out.println("로그인한 멤버id :" + mem_id);
			String nickName = session.getAttribute("nickName").toString();
			System.out.println(nickName);
			p = new Post();
			b = new Board();
			p.setBoard_id(b);
			b.setBrd_id(Integer.parseInt(request.getParameter("brd_id")));
			p.setMem_id(mem_id);
			p.setAdmin_id("admin_id");
			p.setMem_nickName(nickName);
			p.setPost_title(request.getParameter("title"));
			p.setPost_content(request.getParameter("content"));
			service.wirtePost(p);
			forwardURL = "/PostController?type=boardList?board_id=" + b.getBrd_id();
		} 
		else if (type.equals("adminboardwrite")) {
			b = new Board();
			p = new Post();
			String root = "C:/";
			File dir = new File(root + "files/attachedfile");
			if (!dir.exists())
				dir.mkdirs();
			MultipartRequest mr = null;
			int maxPostSize = 1024 * 10000;
			String encoding = "UTF-8";
			try {
				mr = new MultipartRequest(request, root + "files", maxPostSize, encoding, new RenamePolicy());
			} catch (IOException e) {
				e.printStackTrace(); // maxPostSize, Posted content length
			}
			File attachedfile = mr.getFile("uploadFiles");
			if (attachedfile != null) {
				String attachedfileroot = root + "files/attachedfile/" + attachedfile.getName();
				attachedfile.renameTo(new File(attachedfileroot));
				p.setPost_file(attachedfile.getName());
			}
			b.setBrd_id(Integer.parseInt(mr.getParameter("brd_id")));
			p.setBoard_id(b);
			p.setPost_title(mr.getParameter("title"));
			p.setPost_content(mr.getParameter("content"));
			p.setAdmin_id("admin");
			service.wirtePost(p);
			forwardURL = "/PostController?type=adminPost&brd_id=" + mr.getParameter("brd_id");
		} 
		else if (type.equals("searchAll")) {
			if (!"".equals(searchText)) {
				// 검색문자열이 있는 경우
				String mem_nickName = searchText;
				String post_title = searchText;
				String post_content = searchText;
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				System.out.println("brd_id 값은 : " + brd_id);
				data = service.findAll(brd_id, mem_nickName, post_title, post_content);
				System.out.println("data 값 : " + data);
				request.setAttribute("data", data);
				forwardURL = "/user/boards/boardlist.jsp";
			} else {
				//빈문자열을 검색할 경우 = 전체 검색
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				data = service.boardList(brd_id, realPage);
				request.setAttribute("data", data);
				forwardURL = "/user/boards/boardlist.jsp";
			}
		} else if (type.equals("searchTitle")) {
			if (!"".equals(searchText)) {
				String post_title = searchText;
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				ArrayList<Post> list = service.findTitle(brd_id, post_title);
				request.setAttribute("data", list);
				forwardURL = "/user/boards/boardlist.jsp";
			} else {
				//빈문자열을 검색할 경우 = 전체 검색
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				data = service.boardList(brd_id, realPage);
				request.setAttribute("data", data);
				forwardURL = "/user/boards/boardlist.jsp";
			}
		} else if (type.equals("searchWriter")) {
			if (!"".equals(searchText)) {
				String mem_nickName = searchText;
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				ArrayList<Post> list = service.findWriter(brd_id, mem_nickName);
				request.setAttribute("data", list);
				forwardURL = "/user/boards/boardlist.jsp";
			} else {
				//빈문자열을 검색할 경우 = 전체 검색
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				data = service.boardList(brd_id, realPage);
				request.setAttribute("data", data);
				forwardURL = "/user/boards/boardlist.jsp";
			}
		} else if (type.equals("searchContent")) {
			/* forwardURL = "admin/boardMng/board1.jsp"; */
			if (!"".equals(searchText)) {
			String post_content = searchText;
			brd_id = Integer.parseInt(request.getParameter("brd_id"));
			ArrayList<Post> list = service.findContent(brd_id, post_content);
			request.setAttribute("data", list);
			forwardURL = "/user/boards/boardlist.jsp";
			}else {
				//빈문자열을 검색할 경우 = 전체 검색
				brd_id = Integer.parseInt(request.getParameter("brd_id"));
				data = service.boardList(brd_id, realPage);
				request.setAttribute("data", data);
				forwardURL = "/user/boards/boardlist.jsp";
			}
		} else if(type.equals("adminPost")) {
			brd_id = Integer.parseInt(request.getParameter("brd_id"));
			data = service.postList(brd_id);
			request.setAttribute("data", data);
			forwardURL = "/admin/boardMng/board.jsp?&brd_name=" + request.getParameter("brd_name");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
		dispatcher.forward(request, response);
	}
}