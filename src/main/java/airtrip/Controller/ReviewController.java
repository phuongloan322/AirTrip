package airtrip.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.Reviewbean;
import airtrip.Model.bo.BookRoombo;
import airtrip.Model.bo.Reviewbo;

@Controller
public class ReviewController {
	
	private Reviewbo reviewbo = new Reviewbo();
	private BookRoombo bookbo = new  BookRoombo();

	@RequestMapping(value="/post-review/{bookId}/{placeId}")
	public ModelAndView PostReview(Model model, HttpServletRequest request, HttpSession session, @PathVariable long bookId, @PathVariable long placeId) {
		try {
			
			session.removeAttribute("msg");
			String msg = null;
			
			Accountbean accLogin = (Accountbean)session.getAttribute("accLogin");
			if(accLogin == null)
				return new ModelAndView("redirect:/login");
			
			String rate = request.getParameter("rate");
			String content = request.getParameter("content");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String dateSubmit = formatter.format(date);
			
			Reviewbean review = new Reviewbean(Integer.parseInt(rate), content, dateSubmit, placeId, accLogin);
			
			int rs = reviewbo.postReview(review);
			
			if(rs > 0) {
				int rs2 = bookbo.isReviewBookRoom(bookId);
				if(rs2 > 0) {
					msg = "Đánh giá nhà/phòng thành công!";
				}
			}
			else 
				msg = "Không thực hiện được. Vui lòng thực hiện lại!";
			
			session.setAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/bookroom/finish");
	}
}
