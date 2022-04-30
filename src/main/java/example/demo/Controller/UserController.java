package example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import example.demo.Model.User;
import example.demo.Repository.UserRepository;
import example.demo.Service.UserDetailService;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailService userDetailService;

	@GetMapping("/")
	public String hello() {
		return "hello";
	}

	// trang login
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	// load trang đăng kí
	@GetMapping("/Register")
	public String Register(Model model) {
		model.addAttribute("User", new User());
		return "Register";
	}

	// đăng kí tk mới
	@PostMapping("/Register")
	public String Register(@ModelAttribute("User") User user, Model model) {
		// ktra xem username đã đk chưa
		if (userRepository.findByUserName(user.getUserName()) != null) {
			model.addAttribute("message", "Tài khoản đã được dùng");
			return "Register";
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userRepository.save(user);
			return "hello";
		}
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "login";
	}
}
