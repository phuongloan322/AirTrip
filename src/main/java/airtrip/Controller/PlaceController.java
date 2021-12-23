 package airtrip.Controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bean.ReviewReactionbean;
import airtrip.Model.bean.Reviewbean;
import airtrip.Model.bo.Categorybo;
import airtrip.Model.bo.LovePlacebo;
import airtrip.Model.bo.Placebo;
import airtrip.Model.bo.ReviewReactionbo;
import airtrip.Model.bo.Reviewbo;
import airtrip.Model.dao.LovePlacedao;

@Controller
public class PlaceController {
	
	Placebo placeBo = new Placebo();
	Categorybo categoryBo = new Categorybo();
	
	@RequestMapping("/places")
	public ModelAndView Place(Model model, HttpServletRequest request, HttpSession session ) {
		try {
			
			String search = request.getParameter("search");
			session.removeAttribute("msg");
			if(search !=null) {
				session.removeAttribute("placeByCategory");
				
				List<Placebean> searchList = placeBo.getAllPlace(search);
				session.setAttribute("placeList", searchList);
				
				if(search != "" && searchList.size() > 0)
					session.setAttribute("msg", "Kết quả tìm kiếm với " + search +" là: "+ searchList.size() +" lượt");
				search = null;
			}
			else {
				
				List<Placebean> placeList = placeBo.getPlaceByPaging(1, 12);
				session.setAttribute("placeList", placeList);
				
				List<Categorybean> categoryList = categoryBo.getAll();
				session.setAttribute("categoryList", categoryList);
				
			}
			
			int totalPlace = placeBo.getAllPlace().size();
			System.out.println(totalPlace);
			int totalPageNumber = 1;
			if(totalPlace > 0) {
				if(totalPlace % 12 == 0)
					 totalPageNumber = totalPlace/12;
				else
					totalPageNumber = totalPlace/12 + 1;
			}
			session.setAttribute("totalPageNumber", totalPageNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("place");
	}

	@RequestMapping("/places/pagination/{pagenumber}")
	public ModelAndView PlacePaging(Model model, HttpServletRequest request, HttpSession session, @PathVariable int pagenumber ) {
		try {
			
			String search = request.getParameter("search");
			session.removeAttribute("msg");
			if(search !=null) {
				session.removeAttribute("placeByCategory");
				
				List<Placebean> searchList = placeBo.getAllPlace(search);
				session.setAttribute("placeList", searchList);
				
				if(search != "" && searchList.size() > 0)
					session.setAttribute("msg", "Kết quả tìm kiếm với " + search +" là: "+ searchList.size() +" lượt");
				search = null;
			}
			else {
				
				List<Placebean> placeList = placeBo.getPlaceByPaging(pagenumber, 12);
				session.setAttribute("placeList", placeList);
				
				List<Categorybean> categoryList = categoryBo.getAll();
				session.setAttribute("categoryList", categoryList);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("place");
	}
	
	@RequestMapping("/places/{categoryId}")
	public ModelAndView PlaceByCategory(Model model, HttpServletRequest request, HttpSession session, @PathVariable String categoryId) {
		try {
			
				List<Placebean> placeList = placeBo.getPlaceByCategory(categoryId);
				session.setAttribute("placeByCategory", placeList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/places");
	}
	
	@RequestMapping(value="/placeByCategory", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> placeByCategory(
			@RequestParam(name = "categoryId") String categoryId, HttpSession session) {
		
		try {
			
			List<Placebean> placeList = placeBo.getPlaceByCategory(categoryId);
			session.setAttribute("placeByCategory", placeList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/detailplace/{placeId}")
	public ModelAndView DetailPlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
		try {
			
			Placebean placebean = placeBo.getPlaceId(placeId);
			model.addAttribute("detailPlace", placebean);
			
			LilteCategorybean lilteCategorybean = categoryBo.getLitleCategoryId(placebean.getLitleCategoryId());
			model.addAttribute("lilteCategory", lilteCategorybean);
			
			Categorybean categorybean = categoryBo.getCategoryId(lilteCategorybean.getCategoryId());
			model.addAttribute("Category", categorybean);
			
			Reviewbo reviewbean = new Reviewbo();
			List<Reviewbean> reviewList = reviewbean.getReviewByPlace(placeId);
			
			ReviewReactionbo reactionbo = new ReviewReactionbo();
			List<ReviewReactionbean> reactionList = reactionbo.getReactionByReview();
			
			if(reviewList.size() > 0)
			{
				float s = 0, i = 0;
				for(Reviewbean review : reviewList) {
					s += (float)review.getRate();
					i++;
				}
				float rating = s/i;
				model.addAttribute("rating", rating);
			}
			else {
				model.addAttribute("rating", 0);
			}
			
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("reactionList", reactionList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("detailplace");
	}

}
