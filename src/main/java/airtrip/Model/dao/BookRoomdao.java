package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.BookRoombean;
import airtrip.Model.bean.Placebean;

public class BookRoomdao {

	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	Placedao placedao = new Placedao();
	Accountdao accountdao = new Accountdao();
	
	public List<BookRoombean> getBookRoom(long accThueId) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accThueId);
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(accThueId);	//tài khoản người thuê
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept"), rs.getBoolean("isReview")));
		}
		rs.close();
		return ds;
	}
	
	public List<BookRoombean> getBookRoomAcceptById(long accThueId, boolean isAccept) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom WHERE accountId = ? AND isAccept = ? and isReview = 0";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accThueId);
		pst.setBoolean(2, isAccept);	
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	//tài khoản ng thuê
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept"), rs.getBoolean("isReview")));
		}

		return ds;
	}
	
	public List<BookRoombean> getBookRoomFinish(long accThueId, boolean isAccept) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "select * from BookRoom where endDay <= getdate() and accountId = ? and isAccept = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accThueId);
		pst.setBoolean(2, isAccept);	
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	//tài khoản ng thuê
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept"), rs.getBoolean("isReview")));
		}
		rs.close();
		return ds;
	}
	
	public List<BookRoombean> getBookRoomAccept(long accId, boolean isAccept) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom \r\n" + 
				"WHERE isAccept = ? AND placeId IN \r\n" + 
				"(SELECT placeId FROM Place WHERE accountId= ?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setBoolean(1, isAccept);
		pst.setLong(2, accId);	//ng cho thuê
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	//tài khoản ng thuê
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept"), rs.getBoolean("isReview")));
		}
		rs.close();
		return ds;
	}
	
	public List<BookRoombean> getBookRoomAllAccept(long accId) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom \r\n" + 
				"WHERE placeId IN \r\n" + 
				"(SELECT placeId FROM Place WHERE accountId= ?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accId);
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	//tài khoản ng thuê
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept"), rs.getBoolean("isReview")));
		}
		rs.close();
		return ds;
	}
	
	public int addBookRoom(BookRoombean roombean) throws Exception {
		int result = 0;
		String sql = "INSERT INTO BookRoom VALUES(?,?,?,?,?,?,'false', 'false')";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, roombean.getStartDay());
		pst.setString(2, roombean.getEndDay());
		pst.setLong(3, roombean.getTotalPrice());
		pst.setInt(4, roombean.getPeople());
		pst.setLong(5, roombean.getPlace().getPlaceId());
		pst.setLong(6, roombean.getAccount().getAccountId());
		
		result = pst.executeUpdate();
		return result;
	}
	
	public int deleteBookRoom(long bookId) throws Exception {
		int rs = 0;
		String sql = "DELETE FROM BookRoom WHERE bookId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, bookId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int acceptBookRoom(long bookId, boolean isAccept) throws Exception {
		int rs = 0;
		String sql = "UPDATE BookRoom\r\n" + 
				"SET isAccept = ?\r\n" + 
				"WHERE bookId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setBoolean(1, isAccept);
		pst.setLong(2, bookId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int isReviewBookRoom(long bookId) throws Exception {
		int rs = 0;
		String sql = "UPDATE BookRoom SET isReview = 1 WHERE bookId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, bookId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public long daysBetween2Dates(String startDay, String endDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;
        long getDaysDiff = 0;
        
        try {
         date1 = simpleDateFormat.parse(startDay);
         date2 = simpleDateFormat.parse(endDay);

         long getDiff = date2.getTime() - date1.getTime();

         getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
         if(getDaysDiff < 0) getDaysDiff = -getDaysDiff;
        } catch (Exception e) {
         e.printStackTrace();
        }
        return getDaysDiff;
    }
	
}
