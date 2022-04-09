package example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.demo.Model.Product;
@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>{
 
}
