package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enableById(Long id);
}
