package airtrip.Model.dao;

import java.util.HashMap;

import airtrip.Model.bean.Placebean;

public class LovePlacedao {
	Placedao placeDao = new Placedao();
	
	public HashMap<Long, Placebean> AddPlace(long id, HashMap<Long, Placebean> cart) throws Exception {
		Placebean place = placeDao.findById(id);
		if(place != null && cart.containsKey(id)) {		
			cart.remove(id);
		}
		else if(place != null && !cart.containsKey(id)) {		
			cart.put(id, place);
		}
		return cart;
	}
	
	public HashMap<Long, Placebean> DeletePlace(long id, HashMap<Long, Placebean> cart) throws Exception {
		if(cart == null)
			return cart;
		if(cart.containsKey(id))
			cart.remove(id);
		return cart;
	}
}
