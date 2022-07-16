package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
