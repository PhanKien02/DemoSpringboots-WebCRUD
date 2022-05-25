package example.demo.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import example.demo.Entity.ProductEntity;
import example.demo.Repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	
	
	// lấy ra toàn bộ sản phẩm
	public List<ProductEntity> getProducts() {
		return productRepository.findAll();
	}

	public ProductEntity addProduct(ProductEntity product) throws Exception {	
		return productRepository.save(product);
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	// lấy sản phẩm theo id
	public ProductEntity getProduct(Integer id) {
		return productRepository.getById(id);
	}

	public ProductEntity editProduct(ProductEntity product) {
		return productRepository.save(product);
	}

	// lấy sản phẩm theo tên hoặc category
	public List<ProductEntity> getProducts(String nameProduct, String category) {
		return productRepository.findByNameProduct(nameProduct,category);
	}
	// lưu hình ảnh vảo đĩa
	 public void saveFile(MultipartFile imageFile) throws IOException {
		 String folder="D:\\Eclipse\\DemoSpringboots-WebCRUD-1\\src\\main\\resources\\static\\img\\";
		 byte[]bytes=imageFile.getBytes();
		 Path path=Paths.get(folder+imageFile.getOriginalFilename()); 
		 Files.write(path,bytes);		                              
	    }
	 // lấy link image
	 public String  image(int id)  {
		 return productRepository.imgUrl(id);	
	}
}
