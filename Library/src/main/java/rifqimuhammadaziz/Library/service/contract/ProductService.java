package rifqimuhammadaziz.Library.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import rifqimuhammadaziz.Library.dto.ProductDto;
import rifqimuhammadaziz.Library.model.Product;

import java.util.List;

public interface ProductService {
    /* ADMIN */
    List<ProductDto> findAll();
    Product save(MultipartFile imageProduct, ProductDto productDto);
    Product update(MultipartFile imageProduct, ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);
    ProductDto findById(Long id);
    Page<ProductDto> pageProducts(int pageNo);
    Page<ProductDto> searchProducts(int pageNo, String keyword);

    /* CUSTOMER */
    List<Product> getAllProducts();
    List<Product> listViewProducts();
    Product getProductById(Long id);
    List<Product> getRelatedProduct(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);
}
