package projcet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import vo.RApply;

public class ProjcetDaoOracle implements ProjcetDao {
	@Override
	public void apllyinsert(RApply rApply) {
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			con = sql.OracleConnection.getConnection();
			String insertSQL = "insert all\r\n" + 
					  "into r_project values ((SELECT MAX(rpjt_id)+1 FROM r_project), ?, null, ?, ?, sysdate)\r\n" + 
					  "into r_keeper values ((SELECT MAX(rpjt_id)+1 FROM r_keeper), ?, ?, ?, null, ?)\r\n" + 
					  "into R_META values ((SELECT MAX(rpjt_id)+1 FROM R_META),?,?,0,?,?,?,?,null,to_date(?,'RR/MM/DD'))\r\n"
					+ "into r_optin values ((SELECT MAX(rpjt_id)+1 FROM r_optin), 10,?,?,?,?,to_date(?,'RR/MM/DD'))\r\n"
					+ "SELECT * FROM DUAL";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, rApply.getrProject().getMem_id());
			pstmt.setInt(2, rApply.getrProject().getrPJT_state());
			pstmt.setInt(3, rApply.getrProject().getrPJT_progress());
			pstmt.setString(4, rApply.getrKeeper().getR_name());
			pstmt.setString(5, rApply.getrKeeper().getR_profile());
			pstmt.setString(6, rApply.getrKeeper().getR_email());
			pstmt.setInt(7, rApply.getrKeeper().getR_tel());
			pstmt.setString(8,rApply.getrMeta().getrPJT_title());
			pstmt.setString(9,rApply.getrMeta().getrPJT_subTitle());
			pstmt.setInt(10,rApply.getrMeta().getrTarget_amount());
			pstmt.setString(11,rApply.getrMeta().getrPJT_image());
			pstmt.setString(12,rApply.getrMeta().getrPJT_category());
			pstmt.setString(13,rApply.getrMeta().getrPJT_paper());
			pstmt.setString(14, rApply.getrMeta().getrPJT_endDay());
			pstmt.setInt(15, rApply.getrOption().getrPJT_price());
			pstmt.setString(16, rApply.getrOption().getrPJT_name());
			pstmt.setString(17, rApply.getrOption().getrPJT_detail());
			pstmt.setInt(18, rApply.getrOption().getrPJT_limit());
			pstmt.setString(19, rApply.getrOption().getrPJT_send());
			pstmt.setString(20, rApply.getrOption().getrPJT_send());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sql.OracleConnection.close(pstmt, con);
		}
		
	}
}
