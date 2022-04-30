package example.demo.Controller;

import java.util.List;

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

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

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
			model.addAttribute("successMsg", "Thêm sản phẩm thành công");
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
	public String editProduct(@Valid @ModelAttribute("Product") Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "editproduct";
		} else {
			model.addAttribute("successMsg", "sửa sản phẩm thành công");
			productService.editProduct(product);
			return "redirect:/getproduct";
		}
	}

	@PostMapping("/search")
	public String Search(Model model, @Param("nameProduct") String nameProduct) {
		if (nameProduct != "") {
			// Lấy danh sách san pham có ten nameProduct
			List<Product> products = productService.getProducts(nameProduct);
			model.addAttribute(products);
			return "getProduct";
		} else {
			model.addAttribute(productService.getProducts());
			return "getProduct";
		}
	}

}
