package example.demo.Controller;

import java.util.ArrayList;
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

import example.demo.Entity.ProductEntity;
import example.demo.Service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	 @GetMapping("/")
	 public String hello(Model model) {
		 List<ProductEntity> products = new ArrayList<>();
		 products = productService.getProducts();
		 model.addAttribute("products", products);
	 	return "index";
	 }
	@GetMapping("/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("Product", new ProductEntity());
		return "addproduct";
	}

	@PostMapping("/addproduct")
	public String addProduct(@Valid @ModelAttribute("Product") ProductEntity product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addproduct";
		} else {
			model.addAttribute("successMsg", "Thêm sản phẩm thành công");
			productService.addProduct(product);
			return "redirect:/";
		}
	}

	@GetMapping("delete")
	public String deleteproduct(@Param("id") int id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String editProduct(@Param("id") Integer id, Model model) {
		model.addAttribute("Product", productService.getProduct(id));
		return "editproduct";
	}

	@PostMapping("/editproduct")
	public String editProduct(@Valid @ModelAttribute("Product") ProductEntity product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "editproduct";
		} else {
			model.addAttribute("successMsg", "sửa sản phẩm thành công");
			productService.editProduct(product);
			return "redirect:/";
		}
	}

	@PostMapping("/search")
	public String Search(Model model, @Param("nameProduct") String nameProduct) {
		if (nameProduct != "") {
			// Lấy danh sách san pham có ten nameProduct
			List<ProductEntity> products = productService.getProducts(nameProduct);
			model.addAttribute(products);
			return "index";
		} else {
			model.addAttribute(productService.getProducts());
			return "index";
		}
	}

}
