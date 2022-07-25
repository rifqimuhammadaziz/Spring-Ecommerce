package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.model.ShoppingCart;

public interface OrderService {
    void saveOrder(ShoppingCart cart);
    void acceptOrder(Long id);
    void cancelOrder(Long id);
}
