package example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.demo.Model.Product;
import example.demo.Repository.ProductRepository;


@Service
public class ProductService {
	@Autowired
	private  ProductRepository productRepository;	
	
//	 public List<Product> getProducts(Integer limit) {
//	        return Optional.ofNullable(limit)
//	                       .map(value -> productRepository.findAll(PageRequest.of(0, value)).getContent())
//	                       .orElseGet(() -> productRepository.findAll());
//	 }
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}

	public Product addProduct(Product product) {
		return productRepository.save(product)	;
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
}
