package example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import example.demo.Entity.ProductEntity;
import example.demo.Service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ServletContext application;
	
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
	public String addProduct(@Valid @ModelAttribute("Product") ProductEntity product,@RequestParam("image") MultipartFile  iMultipartFile,  BindingResult result, Model model)
			throws Exception {
		if (result.hasErrors()) {
				return "addproduct";
		}
		else {
	       productService.saveFile(iMultipartFile);
	       String folder="\\img\\";
	       String fileName = folder+ iMultipartFile.getOriginalFilename();
			product.setImageUrl(fileName);
			productService.addProduct(product);
		return "redirect:/";
		}
	}

	@GetMapping("delete")
	public String deleteproduct(@Param("id") int id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}

	@GetMapping("/editproduct{id}")
	public String editProduct(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("Product", productService.getProduct(id));
		return "editproduct";
	}

	@PostMapping("/editproduct/{id}")
	public String editProduct(@PathVariable("id") Integer id,@RequestParam("image") MultipartFile iMultipartFile,@Valid  ProductEntity product, BindingResult result,
			Model model) throws Exception {
		if (result.hasErrors()) {
			return "editproduct";
		} else {
			model.addAttribute("successMsg", "sửa sản phẩm thành công");
			if(iMultipartFile.getOriginalFilename() =="")
			product.setImageUrl(productService.image(id));
			else {
				 productService.saveFile(iMultipartFile);
			       String folder="\\img\\";
			       String fileName = folder+ iMultipartFile.getOriginalFilename();
					product.setImageUrl(fileName);
			}
			productService.editProduct(product);
			return "redirect:/";
		}
	}

	@PostMapping("/search")
	public String Search(Model model, @Param("nameProduct") String nameProduct,@Param("category") String category) {
			// Lấy danh sách san pham có ten nameProduct
		if(nameProduct =="" && category =="" )
		{
			model.addAttribute("products", productService.getProducts());
			return "index";
		}
		else {
			
			List<ProductEntity> products = productService.getProducts(nameProduct,category);
			model.addAttribute("products", products);
			return "index";
		}
	}
	}


