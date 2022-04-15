package example.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/login")
	public String loginpage(Model model) {
		return "login";
	}
	@PostMapping("/loginSecurity")
	public String login(User user) {
		return "getproduct";

	}
	@GetMapping("/Register")
	public String Register(Model model) {
		model.addAttribute("User", new User());
		return "Register";
	}

	@PostMapping("/Register")
	public String Register(@Valid @ModelAttribute("User") User user) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userRepository.save(user);
			return "hello";
	}
}
