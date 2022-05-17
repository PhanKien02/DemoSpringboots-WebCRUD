package example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import example.demo.Entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    // tim danh sach san pham theo ten
    @Query(value = "select * from Product as p where p.nameProduct = :nameProduct", nativeQuery = true)
    List<ProductEntity> findByNameProduct(@Param("nameProduct") String nameProduct);
}
