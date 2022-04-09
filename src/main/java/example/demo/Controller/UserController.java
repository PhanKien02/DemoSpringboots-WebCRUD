package example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import example.demo.Model.User;
import example.demo.Repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public String hello() {
		return "Hello";
	}

	@GetMapping("/loginpage")
	public String loginpage(@ModelAttribute("User") User user) {
		user = userRepository.findByUserName(user.getUserName());
		return "loginpage";
	}

	@GetMapping("/Register")
	public String Register(Model model) {
		model.addAttribute("User", new User());
		return "Register";
	}

	@PostMapping("/Register")
	public String Register(@ModelAttribute("User") User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return ("/loginpage");
	}

}
