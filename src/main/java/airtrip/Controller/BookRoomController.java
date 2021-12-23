package airtrip.Controller;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.BookRoombean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bo.BookRoombo;
import airtrip.Model.bo.Placebo;

@Controller
public class BookRoomController {
	
	private Placebo placebo = new Placebo();
	private BookRoombo bookroombo = new BookRoombo();

	@RequestMapping(value="/bookroom/{id}")
	public String BookRoom(Model model, HttpServletRequest request, HttpSession session, @PathVariable long id) {
		try {
			Placebean placebean = placebo.getPlaceId(id);
			Accountbean accLogin = (Accountbean)session.getAttribute("accLogin");
			if(accLogin == null) 
				return "redirect:/login";
			String startDay[] = request.getParameter("startDay").split("[-]");
			String startday = startDay[0]+"-"+startDay[1]+"-"+startDay[2];
			
			String endDay[] = request.getParameter("endDay").split("[-]");
			String endday = endDay[0]+"-"+endDay[1]+"-"+endDay[2];
			
			int people = Integer.parseInt(request.getParameter("people"));
			long accountId = accLogin.getAccountId();
			long totalPrice = (bookroombo.daysBetween2Dates(startday, endday))*placebean.getPrice();
			
			BookRoombean roombean = new BookRoombean(startday, endday, totalPrice, people, placebean, accLogin, false, false);
			int rs = bookroombo.addBookRoom(roombean);
			if(rs > 0) {
				model.addAttribute("msg", "Đặt phòng thành công");
				HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");	
				if(cart == null) {
					cart = new HashMap<Long, Placebean>();
				}
				else {
					cart.remove(placebean.getPlaceId());
				}
				return "redirect:/bookroom";
			}
			else {
				model.addAttribute("msg", "Đặt phòng thất bại");
				return "redirect:/loveplace";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value="/AddBookRoom", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> AddBookRoom(
			@RequestParam(name = "placeId") long placeId
			, @RequestParam(name = "startDay") String startDay
			, @RequestParam(name = "endDay") String endDay
			, @RequestParam(name = "people") int people
			, HttpSession session) {
		try {
			Placebean placebean = placebo.getPlaceId(placeId);
			
			String startDayitem[] = startDay.split("[-]");
			String startday = startDayitem[0]+"-"+startDayitem[1]+"-"+startDayitem[2];
			
			String endDayitem[] = endDay.split("[-]");
			String endday = endDayitem[0]+"-"+endDayitem[1]+"-"+endDayitem[2];
			
			Accountbean accLogin = (Accountbean)session.getAttribute("accLogin");
			long accountId = accLogin.getAccountId();
			long totalPrice = (bookroombo.daysBetween2Dates(startday, endday))*placebean.getPrice();
			
			BookRoombean roombean = new BookRoombean(startday, endday, totalPrice, people, placebean, accLogin, false, false);
			int rs = bookroombo.addBookRoom(roombean);
			if(rs > 0) {
				
				HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");	
				if(cart == null) {
					cart = new HashMap<Long, Placebean>();
				}
				else {
					cart.remove(placebean.getPlaceId());
				}
				return new ResponseEntity<Object>(HttpStatus.OK);
			}
			else 
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping("/bookroom")
	public ModelAndView getAllBookRoom(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			if(accountbean == null) {
				return new ModelAndView("login");
			}
			List<BookRoombean> roomList = bookroombo.getBookRoom(accountbean.getAccountId());
			model.addAttribute("bookroomList", roomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("bookroom");
	}
	
	@RequestMapping("/bookroom/isAccept")
	public ModelAndView getAcceptBookRoom(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			if(accountbean == null) {
				return new ModelAndView("login");
			}
			List<BookRoombean> roomList = bookroombo.getBookRoomAcceptById(accountbean.getAccountId(), true);
			model.addAttribute("bookroomList", roomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("bookroom");
	}
	
	@RequestMapping("/bookroom/noAccept")
	public ModelAndView getNoAcceptBookRoom(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			if(accountbean == null) {
				return new ModelAndView("login");
			}
			List<BookRoombean> roomList = bookroombo.getBookRoomAcceptById(accountbean.getAccountId(), false);
			model.addAttribute("bookroomList", roomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("bookroom");
	}
	
	@RequestMapping("/bookroom/finish")
	public ModelAndView FinishBookRoom(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			if(accountbean == null) {
				return new ModelAndView("login");
			}
			List<BookRoombean> roomList = bookroombo.getBookRoomFinish(accountbean.getAccountId(), true);
			model.addAttribute("bookroomList", roomList);
			model.addAttribute("review", "Đánh giá");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("bookroom");
	}
	
	@RequestMapping(value="/DeleteBookRoom", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> DeleteBookRoom(
			@RequestParam(name = "bookId") long bookId, HttpSession session) {
		
		try {
			
			int rs = bookroombo.deleteBookRoom(bookId);
			
			if(rs > 0)
				return new ResponseEntity<Object>(HttpStatus.OK);
			else 
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
