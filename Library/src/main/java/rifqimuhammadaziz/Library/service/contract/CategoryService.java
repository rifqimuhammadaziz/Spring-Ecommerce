package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.CategoryDto;
import rifqimuhammadaziz.Library.model.Category;
import rifqimuhammadaziz.Library.model.Product;

import java.util.List;

public interface CategoryService {
    /* ADMIN */
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enableById(Long id);
    List<Category> findAllByActivated();

    /* CUSTOMER */
    List<CategoryDto> getCategoryAndProduct();
}
