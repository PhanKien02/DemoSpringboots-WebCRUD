package example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.demo.Model.Product;
import example.demo.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

	public Product getProduct(Integer id) {
		return productRepository.getById(id);
	}

	public Product editProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getProducts(String nameProduct) {
		return productRepository.findByNameProduct(nameProduct);
	}
}
