package airtrip.Model.bo;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.dao.Accountdao;

public class Accountbo {

	Accountdao accdao = new Accountdao();
	
	public List<Accountbean> getAccount() throws Exception {
		return accdao.getAccount();
	}
	
	public Accountbean getAccountById(long accId) throws Exception {
		return accdao.getAccountById(accId);
	}
	
	public Accountbean checkAccount(String username, String password) throws Exception {
		List<Accountbean> accList = accdao.getAccount();
		for(Accountbean acc : accList) {
			if(acc.getUsername().equals(username)) {
				if (acc != null) {
					if (BCrypt.checkpw(password, acc.getPassword())) {
						return acc;
					} else {
						return null;
					}
				}
			}
		}
		return null;
	}
	
	public Accountbean getAccountByUsername(String username) throws Exception {
		return accdao.getAccountByUsername(username);
	}
	
	public int Register(Accountbean acc) throws Exception {
		acc.setPassword(BCrypt.hashpw(acc.getPassword(), BCrypt.gensalt(12)));
		return accdao.Register(acc);
	}
	
	public int EditAccount(Accountbean acc) throws Exception {
		return accdao.EditAccount(acc);
	}
	
	public int EditAboutPassAccount(Accountbean acc) throws Exception {
		acc.setPassword(BCrypt.hashpw(acc.getPassword(), BCrypt.gensalt(12)));
		return accdao.EditAboutPassAccount(acc);
	}
	
	public int EditAvatar(Accountbean account) throws Exception {
		return accdao.EditAvatar(account);
	}
}
