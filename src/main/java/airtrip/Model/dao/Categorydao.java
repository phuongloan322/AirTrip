package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;

public class Categorydao {

	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	DungChung dc = new DungChung();
	
	public Categorybean getCategoryId(String ma) throws Exception {
		Categorybean categorybean = new Categorybean();
		String sql = "SELECT * FROM Category WHERE categoryId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, ma);
		rs = pst.executeQuery();
		while(rs.next()) {
			categorybean = new Categorybean(rs.getString("categoryId"), rs.getString("name"));
			return categorybean;
		}
		rs.close();
		return null;
	}
	
	public List<Categorybean> getAll() throws Exception {
		List<Categorybean> ds = new ArrayList<Categorybean>();
		String sql = "SELECT * FROM Category";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			ds.add(new Categorybean(rs.getString("categoryId"), rs.getString("name")));
		}
		rs.close();
		return ds;
	}
}
