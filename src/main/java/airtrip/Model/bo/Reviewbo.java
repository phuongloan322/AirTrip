package airtrip.Model.bo;

import java.util.List;

import airtrip.Model.bean.Reviewbean;
import airtrip.Model.dao.Reviewdao;

public class Reviewbo {

	private Reviewdao reviewdao = new Reviewdao();
	
	public List<Reviewbean> getReviewByPlace(long placeId) throws Exception{
		return reviewdao.getReviewByPlace(placeId);
	}
	
	public int postReview(Reviewbean review) throws Exception {
		return reviewdao.postReview(review);
	}
}
