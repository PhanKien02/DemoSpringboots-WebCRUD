package example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import example.demo.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from Product as p where p.nameProduct = :nameProduct", nativeQuery = true)
    List<Product> findByNameProduct(@Param("nameProduct") String nameProduct);
}
