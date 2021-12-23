package airtrip.Model.bean;

public class BookRoombean {
	private long bookId;
	private String startDay;
	private String endDay;
	private long totalPrice;
	private int people;
	private Placebean place;
	private Accountbean account;
	private Boolean isAccept;
	private Boolean isReview;
	
	public BookRoombean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookRoombean(long bookId, String startDay, String endDay, long totalPrice, int people, Placebean place,
			Accountbean accountId, Boolean isAccept, Boolean isReview) {
		super();
		this.bookId = bookId;
		this.startDay = startDay;
		this.endDay = endDay;
		this.totalPrice = totalPrice;
		this.people = people;
		this.place = place;
		this.account = accountId;
		this.isAccept = isAccept;
		this.isReview = isReview;
	}
	public BookRoombean(String startDay, String endDay, long totalPrice, int people, Placebean place,
			Accountbean account, Boolean isAccept, Boolean isReview) {
		super();
		this.startDay = startDay;
		this.endDay = endDay;
		this.totalPrice = totalPrice;
		this.people = people;
		this.place = place;
		this.account = account;
		this.isAccept = isAccept;
		this.isReview = isReview;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public Placebean getPlace() {
		return place;
	}
	public void setPlace(Placebean place) {
		this.place = place;
	}
	public Accountbean getAccount() {
		return account;
	}
	public void setAccountId(Accountbean accountId) {
		this.account = account;
	}
	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}
	
	public Boolean getIsReview() {
		return isReview;
	}

	public void setIsReview(Boolean isReview) {
		this.isReview = isReview;
	}
	
}
