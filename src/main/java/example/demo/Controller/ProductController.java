package example.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import example.demo.Service.ProductService;
import example.demo.Model.Product;
import example.demo.Repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/getproduct")
	public String getProduct(Model model) {
		model.addAttribute(productService.getProducts());
		return "getproduct";
	}

	@GetMapping("/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("Product", new Product());
		return "addproduct";
	}

	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute("Product") Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addproduct";
		} else {
			model.addAttribute("successMsg", "Them thanh cong!!");
			productService.addProduct(product);
			return "redirect:/getproduct";
		}
	}

	@GetMapping("delete")
	public String deleteproduct(@Param("id") int id) {
		productService.deleteProduct(id);
		return "redirect:/getproduct";
	}

	@GetMapping("/edit")
	public String editProduct(@Param("id") Integer id, Model model) {
		model.addAttribute("Product", productService.getProduct(id));
		return "editproduct";
	}

	@PostMapping("/editproduct")
	public String editProduct(@Valid @ModelAttribute("Product") Product product) {
		productService.editProduct(product);
		return "redirect:/getproduct";
	}
}
