package airtrip.Model.bo;

import java.util.List;

import airtrip.Model.bean.ReviewReactionbean;
import airtrip.Model.dao.ReviewReactiondao;

public class ReviewReactionbo {

	private ReviewReactiondao reactiondao = new ReviewReactiondao();
	
	public int PostReaction(ReviewReactionbean reaction) throws Exception {
		return reactiondao.PostReaction(reaction);
	}
	
	public int DeletePostReaction(long reactionId) throws Exception {
		return reactiondao.DeletePostReaction(reactionId);
	}
	
	public List<ReviewReactionbean> getReactionByReview() throws Exception {
		return reactiondao.getReactionByReview();
	}
	
	public ReviewReactionbean GetLastReaction() throws Exception {
		return reactiondao.GetLastReaction();
	}
}
