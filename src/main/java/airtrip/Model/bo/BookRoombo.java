package airtrip.Model.bo;

import java.util.List;

import airtrip.Model.bean.BookRoombean;
import airtrip.Model.dao.BookRoomdao;

public class BookRoombo {

	private BookRoomdao bookRoomdao = new BookRoomdao();
	
	public List<BookRoombean> getBookRoom(long accId) throws Exception {
		return bookRoomdao.getBookRoom(accId);
	}
	
	public List<BookRoombean> getBookRoomAcceptById(long accThueId, boolean isAccept) throws Exception {
		return bookRoomdao.getBookRoomAcceptById(accThueId, isAccept);
	}
	
	public List<BookRoombean> getBookRoomFinish(long accThueId, boolean isAccept) throws Exception {
		return bookRoomdao.getBookRoomFinish(accThueId, isAccept);
	}
	
	public int addBookRoom(BookRoombean roombean) throws Exception {
		return bookRoomdao.addBookRoom(roombean);
	}
	
	public int deleteBookRoom(long bookId) throws Exception {
		return bookRoomdao.deleteBookRoom(bookId);
	}
	public long daysBetween2Dates(String startDay, String endDay) {
		return bookRoomdao.daysBetween2Dates(startDay, endDay);
	}
	
	public List<BookRoombean> getBookRoomAccept(long accId, boolean isAccept) throws Exception {
		return bookRoomdao.getBookRoomAccept(accId, isAccept);
	}
	
	public List<BookRoombean> getBookRoomAllAccept(long accId) throws Exception {
		return bookRoomdao.getBookRoomAllAccept(accId);
	}
	
	public int acceptBookRoom(long bookId, boolean isAccept) throws Exception {
		return bookRoomdao.acceptBookRoom(bookId, isAccept);
	}
	
	public int isReviewBookRoom(long bookId) throws Exception {
		return bookRoomdao.isReviewBookRoom(bookId);
	}
}
