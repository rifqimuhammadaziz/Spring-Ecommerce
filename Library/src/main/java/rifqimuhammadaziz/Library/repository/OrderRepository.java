package rifqimuhammadaziz.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rifqimuhammadaziz.Library.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
