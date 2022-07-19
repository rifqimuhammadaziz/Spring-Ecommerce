package rifqimuhammadaziz.Library.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import rifqimuhammadaziz.Library.dto.ProductDto;
import rifqimuhammadaziz.Library.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product save(MultipartFile imageProduct, ProductDto productDto);
    Product update(MultipartFile imageProduct, ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);
    ProductDto findById(Long id);

    Page<ProductDto> pageProducts(int pageNo);
    Page<ProductDto> searchProducts(int pageNo, String keyword);
}
