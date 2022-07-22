package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.model.Product;
import rifqimuhammadaziz.Library.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(Product product, int quantity, Customer customer);
}
