package airtrip.Controller;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.BookRoombean;
import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.DetailPlacebean;
import airtrip.Model.bean.LilteCategorybean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bean.ReviewReactionbean;
import airtrip.Model.bean.Reviewbean;
import airtrip.Model.bo.BookRoombo;
import airtrip.Model.bo.Categorybo;
import airtrip.Model.bo.Placebo;
import airtrip.Model.bo.ReviewReactionbo;
import airtrip.Model.bo.Reviewbo;

@Controller
public class BecomeAHostController extends HttpServlet {
	private static final String UPLOAD_DIRECTORY = "asset/images";   
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	    
	private Categorybo cbo = new Categorybo();
	private Placebo placebo = new Placebo();
	private Categorybo categoryBo = new Categorybo();
	private BookRoombo bookRoombo = new BookRoombo();

	 @Autowired
	 ServletContext servletContext;
	 
	@RequestMapping("/become-a-host")
	public ModelAndView BecomeAHost(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean acc = (Accountbean)session.getAttribute("accLogin");
			if(acc == null)
				return new ModelAndView("redirect:/login");
			
			List<Categorybean> categoryList = cbo.getAll();
			List<LilteCategorybean> litleList = cbo.getLilteCategory();
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("litleList", litleList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("hostindex");
	}
	
	@RequestMapping("/become-a-host/index")
	public ModelAndView BecomeAHostIndex(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean acc = (Accountbean)session.getAttribute("accLogin");
			if(acc == null)
				return new ModelAndView("redirect:/login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("host/index");
	}
	
	@RequestMapping("/become-a-host/listings")
	public ModelAndView BecomeAHostPhongChoThue(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean acc = (Accountbean)session.getAttribute("accLogin");
			if(acc == null)
				return new ModelAndView("redirect:/login");

			List<Placebean> getPlaceByAccId = placebo.getPlaceByAccId(acc.getAccountId());
			session.setAttribute("PlaceByAccId", getPlaceByAccId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("host/phongchothue");
	}
	
	@RequestMapping("/become-a-host/history")
	public ModelAndView BecomeAHostHistory(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean acc = (Accountbean)session.getAttribute("accLogin");
			if(acc == null)
				return new ModelAndView("redirect:/login");
			
			List<BookRoombean> bookroomList = bookRoombo.getBookRoomAccept(acc.getAccountId(), true);
			session.setAttribute("bookroomList", bookroomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("host/history");
	}
	
	@RequestMapping(value="/become-a-host/detailplace/{placeId}")
	public ModelAndView DetailPlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
		try {
			Accountbean acc = (Accountbean)session.getAttribute("accLogin");
			if(acc == null)
				return new ModelAndView("redirect:/login");
			
			Placebean placebean = placebo.getPlaceId(placeId);
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
		return new ModelAndView("/host/detailphongchothue");
	}
	
//	@RequestMapping(value="/become-a-host/detailplace/editdetail/{detailId}")
//	public String EditDetailPlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long detailId) {
//		try {
//			String khach = request.getParameter("khach");
//			String phongngu = request.getParameter("phongngu");
//			String giuong = request.getParameter("giuong");
//			String phongvs = request.getParameter("phongvs");
//			int rs = placebo.EditDetailPlace(Integer.parseInt(khach), Integer.parseInt(phongngu)
//					, Integer.parseInt(giuong), Integer.parseInt(phongvs), detailId);
//			if (rs > 0) {
//				model.addAttribute("editsuccess", "Đã lưu");
//			}
//			else {
//				model.addAttribute("editfail", "Không thể chỉnh sửa");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:"+request.getHeader("Referer");
//	}
	
	@RequestMapping(value="/become-a-host/detailplace/delete-image/{placeId}")
	public String DeleteImagePlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
		try {
			Placebean placebean = placebo.getPlaceId(placeId);
			String images[] = placebean.getImage();
			String image = request.getParameter("image");
			String imageNew = "";
			for(String img : images) {
				if(!img.equals(image))
				{
					imageNew += img+";";
				}
			}
			int rs = placebo.EditImagePlace(imageNew, placeId);
			if (rs > 0) {
				model.addAttribute("deleteimgsuccess", "Xóa thành công");
			}
			else {
				model.addAttribute("deleteimgfail", "Không thể xóa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value="/become-a-host/detailplace/add-image/{placeId}")
	public String AddImagePlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
		try {
			Placebean placebean = placebo.getPlaceId(placeId);
			String images[] = placebean.getImage();
			String imageNew = "";
			for(String img : images) {
				imageNew += img+";";
			}
			
			if(!ServletFileUpload.isMultipartContent(request)) {	//
				request.setAttribute("resultadd", (long)0);		
				return "redirect:/index";
			}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			String uploadPath = servletContext.getRealPath("")
					+ File.separator + UPLOAD_DIRECTORY;		
			File uploadDir = new File(uploadPath);
			System.out.println(uploadDir);
			if(!uploadDir.exists()) {	
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem> fileItems = upload.parseRequest(request);	//lấy về các đối tượng gửi lên
				if(fileItems != null && fileItems.size() > 0) {		
					int i = 0;
					for(FileItem item : fileItems) {		//duyệt các đối tượng gồm file và các control
						if(!item.isFormField()) {			//nếu k phải control => upfile lên
								
							String fileName = new File(item.getName()).getName();		//get tên file	vd: anh1.png
							String filePath = uploadPath + File.separator + fileName;	//get đường dẫn file 
							File storeFile = new File(filePath);						//tạo file
							item.write(storeFile);										//lưu file				
							
							imageNew += fileName + ";";
						}
					}
					
					int rs = placebo.EditImagePlace(imageNew, placeId);
					if (rs > 0) {
						model.addAttribute("addimgsuccess", "Thêm ảnh thành công");
					}
					else {
						model.addAttribute("addimgfail", "Thêm ảnh thất bại");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+request.getHeader("Referer");
	}
	
//	@RequestMapping(value="/become-a-host/detailplace/editplace/{placeId}")
//	public String EditPlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
//		try {
//			String name = request.getParameter("tieude");
//			String detail = request.getParameter("detail");
//			String address = request.getParameter("address");
//			String price = request.getParameter("price");
//			String startDay = request.getParameter("startDay");
//			String endDay = request.getParameter("endDay");
//			String isEmpty = request.getParameter("radio");
//			
//			int rs = placebo.EditPlace(name, detail, address, Long.parseLong(price), startDay, endDay, Boolean.parseBoolean(isEmpty), placeId);
//			if (rs > 0) {
//				model.addAttribute("editplacesuccess", "Đã lưu");
//			}
//			else {
//				model.addAttribute("editplacefail", "Không thể chỉnh sửa");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:/become-a-host/detailplace/{placeId}";
//	}
	
	@RequestMapping("/become-a-host/deletePlace")
	public String DeletePlaceById(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean acc = (Accountbean)session.getAttribute("accLogin");
			String placeId[] = request.getParameterValues("checkbox");
			for(String placeid : placeId) {
				int rs = placebo.DeletePlace(Long.parseLong(placeid));
				if(rs > 0) {
					model.addAttribute("msg", "Xóa thành công");
				}
				else model.addAttribute("msg", "Xóa thất bại");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/become-a-host/listings";
	}
	
	@RequestMapping("/become-a-host/deleteAllPlace")
	public String DeleteAllPlace(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean acc = (Accountbean) session.getAttribute("accLogin");
			int rs = placebo.DeletePlace(acc.getAccountId());
			if (rs > 0) {
				model.addAttribute("msg", "Xóa thành công");
			} else
				model.addAttribute("msg", "Xóa thất bại");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/become-a-host/listings";
	}
	
	
	@RequestMapping("/become-a-host/addNew")
	public String AddHost(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)  {
		Accountbean account = (Accountbean)session.getAttribute("accLogin");
		if(account == null) {
			return "redirect:/login";
		}
		String category = "";
		String litleCategory = "";
		String address = "";
		String khach = "";
		String phongngu = "";
		String giuong = "";
		String phongvs = "";
		String tieude = "";
		String detail = "";
		String price = "";
		String startDay = "";
		String endDay = "";
		String tiennghi = "";
		String image = "";
		
		
		if(!ServletFileUpload.isMultipartContent(request)) {	//
			request.setAttribute("resultadd", (long)0);		
			return "redirect:/index";
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
	    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	    
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
	    upload.setSizeMax(MAX_REQUEST_SIZE);
	    
		String uploadPath = request.getServletContext().getRealPath("")
				+ File.separator + UPLOAD_DIRECTORY;	
		
		File uploadDir = new File(uploadPath);
		System.out.println(uploadDir);
		
		if(!uploadDir.exists()) {	
			uploadDir.mkdir();
		}
		
		try {
			List<FileItem> fileItems = upload.parseRequest(request);	//lấy về các đối tượng gửi lên
			int s = fileItems.size();
			if(fileItems != null && fileItems.size() > 0) {		
				int i = 0;
				for(FileItem item : fileItems) {		//duyệt các đối tượng gồm file và các control
					if(!item.isFormField()) {			//nếu k phải control => upfile lên
							
						String fileName = item.getName();		//get tên file	vd: anh1.png
						String filePath = uploadPath + File.separator + fileName;	//get đường dẫn file 
						File storeFile = new File(filePath);						//tạo file
						item.write(storeFile);										//lưu file				
						
						image += fileName + ";";
					}
					else {
						switch (item.getFieldName()) {
						case "category":
							category = item.getString();
							break;
							
						case "litleCategory":
							litleCategory = item.getString();
							break;
							
						case "address":
							address = item.getString("UTF-8");
							break;
							
						case "khach":
							khach = item.getString();
							break;
							
						case "phongngu":
							phongngu = item.getString();
							break;
							
						case "giuong":
							giuong = item.getString();
							break;
							
						case "phongvs":
							phongvs = item.getString();
							break;
							
						case "tieude":
							tieude = item.getString("UTF-8");
							break;
							
						case "detail":
							detail = item.getString("UTF-8");
							break;
							
						case "price":
							price = item.getString();
							break;
							
						case "startDay":
							startDay = item.getString();
							break;
							
						case "endDay":
							endDay = item.getString();
							break;
							
						case "tiennghi":
							tiennghi += item.getString("UTF-8")+";";
							break;
						}
					}
					i++;
					
				}
				
				DetailPlacebean detailPlace = new DetailPlacebean(Integer.parseInt(khach), Integer.parseInt(phongngu)
						, Integer.parseInt(giuong), Integer.parseInt(phongvs), tiennghi.split("[;]"));
				int rs1 = placebo.AddDetailPlace(detailPlace);
				
				Placebean placebean = new Placebean(tieude, image.split("[;]"), address, Long.parseLong(price), account.getPhone(),
						startDay, endDay, detail, true, litleCategory, detailPlace, account);
				int rs2 = placebo.AddPlace(placebean);
				if (rs2 > 0) {
					return "redirect:/become-a-host/listings";
				} else return "redirect:/become-a-host?msg=ERROR";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/become-a-host/index";
	}
	
	@RequestMapping(value="/become-a-host/upcoming/no-accept")
	public ModelAndView noAccept(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			List<BookRoombean> getBookRoomNoAcceptList = bookRoombo.getBookRoomAccept(accountbean.getAccountId(), false);
			model.addAttribute("acceptList", getBookRoomNoAcceptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("host/datphong");
	}
	
	@RequestMapping(value="/become-a-host/upcoming/is-accept")
	public ModelAndView isAccept(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			List<BookRoombean> getBookRoomisAcceptList = bookRoombo.getBookRoomAccept(accountbean.getAccountId(), true);
			model.addAttribute("acceptList", getBookRoomisAcceptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("host/datphong");
	}
	
	@RequestMapping(value="/become-a-host/upcoming/all-accept")
	public ModelAndView allAccept(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
			List<BookRoombean> getBookRoomAllAcceptList = bookRoombo.getBookRoomAllAccept(accountbean.getAccountId());
			model.addAttribute("acceptList", getBookRoomAllAcceptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("host/datphong");
	}
	
	@RequestMapping(value="/become-a-host/upcoming/accept/{bookId}")
	public String acceptBookRoom(Model model, HttpServletRequest request, HttpSession session, @PathVariable long bookId) {
		try {
			int rs = bookRoombo.acceptBookRoom(bookId, true);
			if(rs > 0) {
				model.addAttribute("acceptsuccess", "Xác nhận cho thuê phòng thành công!");
			}
			else {
				model.addAttribute("acceptfail", "Thực hiện thất bại!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value="/become-a-host/detailplace/editdetail", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> updateDetail(
			@RequestParam(name = "detailId") long detailId,
			@RequestParam(name = "khach") int khach,
			@RequestParam(name = "phongngu") int phongngu,
			@RequestParam(name = "giuong") int giuong,
			@RequestParam(name = "phongvs") int phongvs) {
		
		try {
			int rs = placebo.EditDetailPlace(khach, phongngu, giuong, phongvs, detailId);
			
			if(rs > 0)
				return new ResponseEntity<Object>(HttpStatus.OK);
			else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/become-a-host/detailplace/editplace", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> EditPlace (
			@RequestParam(name = "placeId") long placeId,
			@RequestParam(name = "tieude") String tieude,
			@RequestParam(name = "detail") String detail,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "price") long price,
			@RequestParam(name = "startDay") String startDay,
			@RequestParam(name = "endDay") String endDay,
			@RequestParam(name = "isEmpty") boolean isEmpty) {
		
		try {
			int rs = placebo.EditPlace(tieude, detail, address, price, startDay, endDay, isEmpty, placeId);
			
			if(rs > 0)
				return new ResponseEntity<Object>(HttpStatus.OK);
			else
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/become-a-host/add-new", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> AddNew(HttpServletRequest request ,HttpServletResponse response, HttpSession session,
			@RequestParam("litleCategory") String litleCategory,
			@RequestParam("address") String address,
			@RequestParam("phongngu") int phongngu,
			@RequestParam("khach") int khach,
			@RequestParam("giuong") int giuong,
			@RequestParam("phongvs") int phongvs,
			@RequestParam("tiennghi") String tiennghi,
			@RequestParam("tieude") String tieude,
			@RequestParam("detail") String detail,
			@RequestParam("price") long price,
			@RequestParam("startDay") String startDay,
			@RequestParam("endDay") String endDay,
			@RequestParam(value = "file-0", required = false) MultipartFile file0
/*			@RequestParam(value = "file-1", required = false) MultipartFile file1,
			@RequestParam(value = "file-2", required = false) MultipartFile file2,
			@RequestParam(value = "file-3", required = false) MultipartFile file3,
			@RequestParam(value = "file-4", required = false) MultipartFile file4*/
			)  {
		
		try {
			String newImage = null;
			Accountbean account = (Accountbean)session.getAttribute("accLogin");
			
			if(file0 != null) {
				String endFile = file0.getOriginalFilename().substring(file0.getOriginalFilename().lastIndexOf("."));
				String url = request.getServletContext().getRealPath("") + File.separator + "asset" + File.separator
						+ "images" + File.separator;
				
				UUID uuid = UUID.randomUUID();
				newImage = uuid.toString() + endFile;
				String fileimg = url + File.separator + newImage;
				File newImageFile = new File(fileimg);
				file0.transferTo(newImageFile);
				
				DetailPlacebean detailPlace = new DetailPlacebean(khach, phongngu, giuong, phongvs, tiennghi.split("[;]"));
				int rs1 = placebo.AddDetailPlace(detailPlace);
				
				Placebean placebean = new Placebean(tieude, newImage.split(";"), address, price, account.getPhone(),
						startDay, endDay, detail, true, litleCategory, detailPlace, account);
				
				int rs2 = placebo.AddPlace(placebean);
				
				if(rs2 > 0)
					return new ResponseEntity<Object>(HttpStatus.OK);
				else
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
