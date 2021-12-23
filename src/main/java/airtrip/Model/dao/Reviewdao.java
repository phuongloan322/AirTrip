package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bean.Reviewbean;

public class Reviewdao {

	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	
	private Accountdao accdao = new Accountdao();
	
	public List<Reviewbean> getReviewByPlace(long placeId) throws Exception{
		List<Reviewbean> reviewList = new ArrayList<Reviewbean>();
		
		String sql = "select * from Review where placeId = ?";
		
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, placeId);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
					
			reviewList.add(new Reviewbean(rs.getLong("reviewId"), rs.getInt("rate"), rs.getString("content"), rs.getString("dateSubmit")
					, rs.getLong("placeId"), account));
		}
		
		rs.close();
		
		return reviewList;
	}
	
	public int postReview(Reviewbean review) throws Exception {
		int rs = 0;
		String sql = "INSERT Review\r\n" + 
					"VALUES(?, ?, ?, ?, ?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, review.getPlaceId());
		pst.setLong(2, review.getAccount().getAccountId());
		pst.setInt(3, review.getRate());
		pst.setString(4, review.getContent());
		pst.setString(5, review.getDateSubmit());
		
		rs = pst.executeUpdate();
		
		return rs;
		
	}
}
