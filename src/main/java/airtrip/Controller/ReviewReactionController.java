package airtrip.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.ReviewReactionbean;
import airtrip.Model.bo.ReviewReactionbo;

@Controller
public class ReviewReactionController {
	
	private ReviewReactionbo reactionbo = new ReviewReactionbo();
	
	@RequestMapping(value="/review-reaction", method = RequestMethod.POST )
	public @ResponseBody String PostReaction(HttpSession session, HttpServletResponse response
			,@RequestParam(name = "reviewId") String reviewId, @RequestParam(name = "detail") String detail) {

		Accountbean account = (Accountbean) session.getAttribute("accLogin");
		Gson data = new Gson();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateSubmit = formatter.format(date);

		ReviewReactionbean reactionbean = new ReviewReactionbean(Long.parseLong(reviewId), account, detail, dateSubmit);
		try {
			int rs = reactionbo.PostReaction(reactionbean);
			reactionbean = reactionbo.GetLastReaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String reaction = data.toJson(reactionbean);
		
		return reaction;
	}
	
	@RequestMapping(value="/DeleteReaction", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> DeleteReaction(
			@RequestParam(name = "reactionId") String reactionId, HttpSession session) {
		
		try {
			
			int rs = reactionbo.DeletePostReaction(Long.parseLong(reactionId));
			
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
