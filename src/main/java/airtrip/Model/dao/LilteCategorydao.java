package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;

public class LilteCategorydao {

	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	
	public LilteCategorybean getLitleCategoryId(String ma) throws Exception {
		LilteCategorybean lilteCategorybean = new LilteCategorybean();
		String sql = "SELECT * FROM LitleCategory WHERE litleCategoryId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, ma);
		rs = pst.executeQuery();
		while(rs.next()) {
			lilteCategorybean = new LilteCategorybean(rs.getString("litleCategoryId"), rs.getString("litleName")
					, rs.getString("detail"), rs.getString("categoryId"));
			return lilteCategorybean;
		}
		rs.close();
		return null;
	}
	
	public List<LilteCategorybean> getAll() throws Exception {
		List<LilteCategorybean> ds = new ArrayList<LilteCategorybean>();
		String sql = "SELECT * FROM LitleCategory";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			ds.add(new LilteCategorybean(rs.getString("litleCategoryId"), rs.getString("litleName")
					, rs.getString("detail"), rs.getString("categoryId")));
		}
		rs.close();
		return ds;
	}
}
