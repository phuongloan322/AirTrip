package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Accountbean;

public class Accountdao {
	
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	DungChung dc = new DungChung();

	public List<Accountbean> getAccount() throws Exception {
		List<Accountbean> accList = new ArrayList<Accountbean>();
		String sql = "SELECT * FROM Account";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			accList.add(new Accountbean(rs.getInt("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image")));
		}
		rs.close();
		return accList;
	}
	
	public Accountbean getAccountById(long accId) throws Exception {
		Accountbean accountbean = null;
		String sql = "SELECT * FROM Account WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accId);
		rs = pst.executeQuery();
		while(rs.next()) {
			accountbean = new Accountbean(rs.getInt("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image"));
		}
		rs.close();
		return accountbean;
	}
	
	public Accountbean getAccountByUsername(String username) throws Exception {
		Accountbean accountbean = null;
		String sql = "SELECT * FROM Account WHERE username = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, username);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			accountbean = new Accountbean(rs.getInt("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image"));
		}
		rs.close();
		return accountbean;
	}
	
	public int Register(Accountbean account) throws Exception {
		int rs = 0;
		String sql = "INSERT Account\r\n" + 
					"VALUES (?,?,?,?,?,?,?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, account.getName());
		pst.setString(2, account.getAddress());
		pst.setString(3, account.getPhone());
		pst.setString(4, account.getEmail());
		pst.setString(5, account.getUsername());
		pst.setString(6, account.getPassword());
		pst.setString(7, account.getImage());
		
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditAccount(Accountbean account) throws Exception {
		int rs = 0;
		String sql = "UPDATE Account\r\n" + 
				"SET name = ?, phone = ?, address = ?, email = ?\r\n" + 
				"WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, account.getName());
		pst.setString(2, account.getPhone());
		pst.setString(3, account.getAddress());
		pst.setString(4, account.getEmail());
		pst.setLong(5, account.getAccountId());
		
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditAboutPassAccount(Accountbean account) throws Exception {
		int rs = 0;
		String sql = "UPDATE Account\r\n" + 
				"SET name = ?, phone = ?, address = ?, email = ?, password = ?\r\n" + 
				"WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, account.getName());
		pst.setString(2, account.getPhone());
		pst.setString(3, account.getAddress());
		pst.setString(4, account.getEmail());
		pst.setString(5, account.getPassword());
		pst.setLong(6, account.getAccountId());
		
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditAvatar(Accountbean account) throws Exception {
		int rs = 0;
		String sql = "UPDATE Account SET image = ? WHERE accountId = ?";
		
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, account.getImage());
		pst.setLong(2,  account.getAccountId());
		
		rs = pst.executeUpdate();
		
		return rs;
	}
}
