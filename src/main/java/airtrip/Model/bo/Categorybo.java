package airtrip.Model.bo;

import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;
import airtrip.Model.dao.Categorydao;
import airtrip.Model.dao.LilteCategorydao;

public class Categorybo {

	private Categorydao categorydao = new Categorydao();
	private LilteCategorydao lilteCategorydao = new LilteCategorydao();
	
	public Categorybean getCategoryId(String ma) throws Exception {
		return categorydao.getCategoryId(ma);
	}
	
	public LilteCategorybean getLitleCategoryId(String ma) throws Exception {
		return lilteCategorydao.getLitleCategoryId(ma);
	}
	
	public List<Categorybean> getAll() throws Exception {
		return categorydao.getAll();
	}
	
	public List<LilteCategorybean> getLilteCategory() throws Exception {
		return lilteCategorydao.getAll();
	}
}
