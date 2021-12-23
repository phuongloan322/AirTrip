package airtrip.Model.bean;

public class ReviewReactionbean {

	private long reactionId;
	private long reviewId;
	private Accountbean account;
	private String details;
	private String dateSubmit;
	public ReviewReactionbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewReactionbean(long reactionId, long reviewId, Accountbean account, String details, String dateSubmit) {
		super();
		this.reactionId = reactionId;
		this.reviewId = reviewId;
		this.account = account;
		this.details = details;
		this.dateSubmit = dateSubmit;
	}
	
	public ReviewReactionbean(long reviewId, Accountbean account, String details, String dateSubmit) {
		super();
		this.reviewId = reviewId;
		this.account = account;
		this.details = details;
		this.dateSubmit = dateSubmit;
	}
	public long getReactionId() {
		return reactionId;
	}
	public void setReactionId(long reactionId) {
		this.reactionId = reactionId;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public Accountbean getAccount() {
		return account;
	}
	public void setAccount(Accountbean account) {
		this.account = account;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getDateSubmit() {
		return dateSubmit;
	}
	public void setDateSubmit(String dateSubmit) {
		this.dateSubmit = dateSubmit;
	}
	
	
}
