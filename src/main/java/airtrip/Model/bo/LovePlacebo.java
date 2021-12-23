package airtrip.Model.bo;

import java.util.HashMap;

import airtrip.Model.bean.Placebean;
import airtrip.Model.dao.LovePlacedao;
import airtrip.Model.dao.Placedao;

public class LovePlacebo {
	LovePlacedao placeDao = new  LovePlacedao();
	
	public HashMap<Long, Placebean> AddPlace(long id, HashMap<Long, Placebean> cart) throws Exception {
		return placeDao.AddPlace(id, cart);
	}
	
	public HashMap<Long, Placebean> DeletePlace(long id, HashMap<Long, Placebean> cart) throws Exception {
		return placeDao.DeletePlace(id, cart);
	}
}
