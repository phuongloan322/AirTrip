package airtrip.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import airtrip.Model.bean.Placebean;
import airtrip.Model.bo.LovePlacebo;

@Controller
public class LovePlaceController {
	
	private LovePlacebo lovePlacebo = new LovePlacebo();
	
	@RequestMapping("/loveplace")
	public ModelAndView LovePlace(Model model, HttpServletRequest request, HttpSession session) {
		try {
			HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");	
			if(cart == null) {
				cart = new HashMap<Long, Placebean>();
			}
			session.setAttribute("Cart", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("loveplace");
	}
	
	@RequestMapping(value="/AddLovePlace", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> AddLovePlace(
			@RequestParam(name = "placeId") long placeId, HttpSession session) {
		
		try {
			HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");
			if(cart == null) {
				cart = new HashMap<Long, Placebean>();
			}
			cart = lovePlacebo.AddPlace(placeId, cart);
			session.setAttribute("Cart", cart);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
//	@RequestMapping(value="/DeleteLovePlace", method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity<Object> DeleteLovePlace(
//			@RequestParam(name = "placeId") long placeId, HttpSession session) {
//		
//		try {
//			HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");
//			if(cart == null) {
//				cart = new HashMap<Long, Placebean>();
//			}
//			cart = lovePlacebo.DeletePlace(placeId, cart);
//			session.setAttribute("Cart", cart);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
//	
	
	@RequestMapping(value="/AddLovePlace/{id}")
	public String AddPlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long id) {
		try {
			HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");
			if(cart == null) {
				cart = new HashMap<Long, Placebean>();
			}
			cart = lovePlacebo.AddPlace(id, cart);
			session.setAttribute("Cart", cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+request.getHeader("Referer");
	}
//	
//	@RequestMapping(value="/DeleteLovePlace/{id}")
//	public String DeletePlace(HttpServletRequest request, HttpSession session, @PathVariable long id) {
//		try {
//			HashMap<Long, Placebean> cart = (HashMap<Long, Placebean>)session.getAttribute("Cart");
//			if(cart == null) {
//				cart = new HashMap<Long, Placebean>();
//			}
//			cart = lovePlacebo.DeletePlace(id, cart);
//			session.setAttribute("Cart", cart);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:"+request.getHeader("Referer");
//	}
}
