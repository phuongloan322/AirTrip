package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.ReviewReactionbean;
import airtrip.Model.bean.Reviewbean;

public class ReviewReactiondao {
	
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();

	public int PostReaction(ReviewReactionbean reaction) throws Exception {
		int rs = 0;
		String sql = "insert ReviewReaction\r\n" + 
				"values (?, ?, ?, ?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, reaction.getReviewId());
		pst.setLong(2, reaction.getAccount().getAccountId());
		pst.setString(3, reaction.getDetails());
		pst.setString(4, reaction.getDateSubmit());
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int DeletePostReaction(long reactionId) throws Exception {
		int rs = 0;
		String sql = "delete ReviewReaction where reactionId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, reactionId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public List<ReviewReactionbean> getReactionByReview() throws Exception{
		List<ReviewReactionbean> reactionList = new ArrayList<ReviewReactionbean>();
		
		Accountdao accountdao = new Accountdao();
		
		String sql = "SELECT * FROM ReviewReaction ";
		
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			Accountbean account = accountdao.getAccountById(rs.getLong("accountId"));
					
			reactionList.add(new ReviewReactionbean(rs.getLong("reactionId"), rs.getLong("reviewId"), account
					, rs.getString("details"), rs.getString("dateSubmit")));
		}
		
		rs.close();
		
		return reactionList;
	}
	
	public ReviewReactionbean GetLastReaction() throws Exception {
		ReviewReactionbean reactionbean = new ReviewReactionbean();
		
			Accountdao accountdao = new Accountdao();
			String sql = "select * from ReviewReaction where reactionId in (\r\n" + 
						"SELECT MAX(reactionId) FROM ReviewReaction)";
			dc.KetNoi();
			st = dc.cn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Accountbean account = accountdao.getAccountById(rs.getLong("accountId"));
				
				reactionbean = new ReviewReactionbean(rs.getLong("reactionId"), rs.getLong("reviewId"), account
						, rs.getString("details"), rs.getString("dateSubmit"));
			}
		
		return reactionbean;
	}
	
}
