package service;

import java.util.List;

import dao.MemberDAO;
import dao.MemberDAOOracle;
import vo.Member;

public class MemberService implements MemberInterface{
	/*
	 	1. 인터페이스 타입의 DAOOracle 객체 생성
	 	2. findXX메소드를 생성하고 컨트롤러로 1, -1값 리턴
	*/
	
	private MemberDAO dao = new MemberDAOOracle();
	
	@Override
	public List<Member> findAll() throws Exception {
		return dao.selectAll();
	}
	
	@Override
	public Member findById(String mem_userId) throws Exception {
		Member member = dao.selectById(mem_userId);
		return member;
	}
	
	@Override
	public int findCount() throws Exception{
		//멤버테이블 행수 출력
		return dao.selectCount();
	}
	
	public static void main(String[] args) {
		MemberService service = new MemberService();
		try {
			List<Member> list = service.findAll();
			System.out.println("findByAll() 결과 : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member idCheck(String mem_userId) throws Exception {
		Member member = dao.idCheck(mem_userId);
		
		if(member != null ) {
			throw new Exception("이미 사용중인 아이디 입니다");
		} else {
			throw new Exception("사용 가능한 아이디 입니다.");
			
		}

		
	
	
	}
	
}