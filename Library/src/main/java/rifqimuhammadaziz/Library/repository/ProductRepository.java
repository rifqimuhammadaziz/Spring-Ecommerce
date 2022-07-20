package rifqimuhammadaziz.Library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /* ADMIN */

    @Query("SELECT p FROM Product p")
    Page<Product> pageProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.description LIKE %?1% OR p.name LIKE %?1%")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.description LIKE %?1% OR p.name LIKE %?1%")
    List<Product> searchProductsList(String keyword);


    /* CUSTOMER */

    @Query("SELECT p FROM Product p WHERE p.is_activated = true AND p.is_deleted = false")
    List<Product> getAllProducts();

    @Query(value = "SELECT * FROM products p WHERE p.is_activated = true AND p.is_deleted = false ORDER BY rand() ASC LIMIT 4", nativeQuery = true)
    List<Product> listViewProducts();

    @Query(value = "SELECT * FROM products p INNER JOIN categories c ON c.category_id = p.category_id WHERE p.category_id = ?1", nativeQuery = true)
    List<Product> getRelatedProduct(Long categoryId);
}
