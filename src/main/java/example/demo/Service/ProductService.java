package example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.demo.Entity.ProductEntity;
import example.demo.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<ProductEntity> getProducts() {
		return productRepository.findAll();
	}

	public ProductEntity addProduct(ProductEntity product) {
		return productRepository.save(product);
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

	public ProductEntity getProduct(Integer id) {
		return productRepository.getById(id);
	}

	public ProductEntity editProduct(ProductEntity product) {
		return productRepository.save(product);
	}

	public List<ProductEntity> getProducts(String nameProduct) {
		return productRepository.findByNameProduct(nameProduct);
	}
}
