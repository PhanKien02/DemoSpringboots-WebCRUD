package example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.demo.Entity.UserEntity;
import example.demo.Service.UserDetailService;

@Controller
public class UserController {
	
	@Autowired
	UserDetailService userDetailService;
	
	// trang login
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	// load trang đăng kí
	@GetMapping("/Register")
	public String Register(Model model) {
		model.addAttribute("UserEntity", new UserEntity());
		return "Register";
	}

	// đăng kí tk mới
	@PostMapping("/Register")
	public String Register(@ModelAttribute("UserEntity") UserEntity userEntity, Model model, BindingResult result) {
		// ktra xem username đã đk chưa
		if (userDetailService.findByUserName(userEntity.getUserName()) != null ) {
			model.addAttribute("message", "Tên tài khoản đã được dùng");
			return "Register";
		} 
		else if (userDetailService.findByEmail(userEntity.getEmail()) != null) {
			model.addAttribute("message", "Email đã được dùng");
			return "Register";
		}
		else {
			if (result.hasErrors()) {
				return "Register";
			} else {
			userDetailService.Register(userEntity);
			return "login";
			}
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}

	@GetMapping("/getUser")
	public String getAllUser(Model model) {
		List<UserEntity> userEntedites = userDetailService.getAllUser();
		model.addAttribute("userList" ,userEntedites);
		return"User";
	}
	@GetMapping("/edit_profile/{user_id}")
	public String editProfile(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("User", userDetailService.findById(id));
		return "userProfile";
	}
}
