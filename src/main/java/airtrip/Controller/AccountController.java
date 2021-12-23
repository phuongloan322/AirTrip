package airtrip.Controller;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import airtrip.Capcha.VerifyUtils;
import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.DetailPlacebean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bo.Accountbo;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;

@Controller
public class AccountController {
	private static final String UPLOAD_DIRECTORY = "asset/images";   
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
	private Accountbo accbo = new Accountbo();
	
	@RequestMapping("/login")
	public ModelAndView checkLogin(Model model, HttpServletRequest request, HttpSession session) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			Accountbean acc = accbo.checkAccount(username, password);
			if(username == null || password == null) {
				return new ModelAndView("login");
			}
			if(acc != null) {
				session.setAttribute("accLogin", acc); 
				return new ModelAndView("redirect:index");
			}
			else {
				model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác ");
				return new ModelAndView("login");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping("/register")
	public ModelAndView Register(Model model, HttpServletRequest request, HttpSession session) {
		try {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			Accountbean acc = new Accountbean(name, address, phone, email, username, password, "a0.jpg");

			boolean valid = true;
			String errorString = "";
			
			String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			
			valid = VerifyUtils.verify(gRecaptchaResponse);
			if(username==null) {
				return new ModelAndView("register");
			}
			else if (!valid) {
	             model.addAttribute("error", "Captcha invalid!");
	         }
			else if(accbo.getAccountByUsername(username) == null) {
				if(!password.equals(repassword)) {
					model.addAttribute("error", "Xác nhận mật khẩu không chính xác!");
				}
				else {
					int rs = accbo.Register(acc);
					if(rs > 0) {
						return new ModelAndView("login");
					}
					else model.addAttribute("error", "Đăng ký thất bại");
				}
			}
			else {
				model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("register");
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session) {
		Accountbean accountbean = (Accountbean)session.getAttribute("accLogin");
		if(accountbean != null) {
			session.removeAttribute("accLogin");
		}
		return "redirect:/login";
	}
		
	@RequestMapping(value="/manager-account")
	public ModelAndView SaveManagerAccount(Model model, HttpServletRequest request, HttpSession session) {
		try {
			Accountbean accLogin = (Accountbean) session.getAttribute("accLogin");
			if (accLogin == null) {
				return new ModelAndView("login");
			}
			
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			String passwordnew = request.getParameter("passwordnew");
			String repasswordnew = request.getParameter("repasswordnew");
			if(email.equals(accLogin.getEmail()) && name.equals(accLogin.getName()) && address.equals(accLogin.getAddress())
					&& phone.equals(accLogin.getPhone()) && password == "")		{
				return new ModelAndView("managerAccount");
			}
			else if(password == null ||password == "") {	//k thay password
				Accountbean acc = new Accountbean(accLogin.getAccountId(), name, address, phone, email, username, "", null);
				int rs = accbo.EditAccount(acc);
				if(rs > 0) {
					model.addAttribute("success", "Thay đổi thành công!");
				}
				else model.addAttribute("error", "Thay đổi thất bại!");
			}
			else {	//thay password
				
				if (BCrypt.checkpw(password, accLogin.getPassword())) {	//pass cũ đúng
					if(passwordnew.equals(repasswordnew)) {	//nhập mk mới đúng
						Accountbean acc = new Accountbean(accLogin.getAccountId(), name, address, phone, email, username, passwordnew, null);
						int rs = accbo.EditAboutPassAccount(acc);
						if(rs > 0) {
							model.addAttribute("success", "Thay đổi thành công!");
						}
						else model.addAttribute("error", "Thay đổi thất bại!");
					}
					else {
						model.addAttribute("error", "Xác nhận mật khẩu mới không chính xác!");
					}
				}
				else {	//pass cũ sai
					model.addAttribute("error", "Nhập mật khẩu cũ không chính xác!");
				}
			}
			Accountbean accUpdate = accbo.getAccountByUsername(username);
			session.setAttribute("accLogin", accUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerAccount");
	}
	
	@RequestMapping(value="/manager-account/edit-image")
	public ModelAndView AddHost(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session)  {
		Accountbean account = (Accountbean)session.getAttribute("accLogin");
		if(account == null) {
			return new ModelAndView("redirect:/login");
		}
		
		session.removeAttribute("msg");
		session.removeAttribute("error");
		
		if(!ServletFileUpload.isMultipartContent(request)) {	
			return new ModelAndView("redirect:/index");
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
			List<FileItem> fileItems = upload.parseRequest(request);	
			int s = fileItems.size();
			if(fileItems != null && fileItems.size() > 0) {		
				for(FileItem item : fileItems) {		
					if(!item.isFormField()) {			
							
						String fileName = item.getName();		
						String filePath = uploadPath + File.separator + fileName;	
						File storeFile = new File(filePath);						
						item.write(storeFile);										//lưu file				
						String imageNew = fileName;
						account.setImage(imageNew);
					}
				}
			}
			
			int rs = accbo.EditAvatar(account);
			if(rs > 0) {
				session.setAttribute("msg", "Lưu thành công");
			}
			else {
				session.setAttribute("error", "Thực hiện thất bại. Vui longf thực hiện lại");
			}
			
			Accountbean accUpdate = accbo.getAccountById(account.getAccountId());
			session.setAttribute("accLogin", accUpdate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/manager-account");
	}
}
