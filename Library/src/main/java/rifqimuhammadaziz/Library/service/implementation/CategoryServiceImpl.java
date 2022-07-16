package rifqimuhammadaziz.Library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.model.Category;
import rifqimuhammadaziz.Library.repository.CategoryRepository;
import rifqimuhammadaziz.Library.service.contract.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        Category newCategory = new Category(category.getName());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category editCategory = new Category();
        editCategory.setName(category.getName());
        editCategory.set_activated(category.is_activated());
        editCategory.set_deleted(category.is_deleted());
        return categoryRepository.save(editCategory);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id).get();
        if (category.is_deleted() == true) {

        }
        category.set_deleted(true);
        category.set_activated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enableById(Long id) {
        Category category = categoryRepository.findById(id).get();
        category.set_deleted(false);
        category.set_activated(true);
        categoryRepository.save(category);
    }
}
