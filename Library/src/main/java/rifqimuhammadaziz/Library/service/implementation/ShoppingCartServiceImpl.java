package rifqimuhammadaziz.Library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.model.CartItem;
import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.model.Product;
import rifqimuhammadaziz.Library.model.ShoppingCart;
import rifqimuhammadaziz.Library.repository.CartItemRepository;
import rifqimuhammadaziz.Library.repository.ShoppingCartRepository;
import rifqimuhammadaziz.Library.service.contract.ShoppingCartService;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart addItemToCart(Product product, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
        }

        Set<CartItem> cartItems = cart.getCartItem();
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if (cartItems == null) {
            cartItems = new HashSet<>();
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            }
        } else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity());
                cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * product.getCostPrice()));
                cartItemRepository.save(cartItem);
            }
        }
        cart.setCartItem(cartItems);

        int totalItems = totalItems(cart.getCartItem());
        cart.setTotalItems(totalItems);

        double totalPrice = totalPrice(cart.getCartItem());
        cart.setTotalPrices(totalPrice);

        cart.setCustomer(customer);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart updateItemInCart(Product product, int quantity, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem item = findCartItem(cartItems, product.getId());
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getCostPrice());
        cartItemRepository.save(item);

        int totalItems = totalItems(cartItems);
        cart.setTotalItems(totalItems);

        double totalPrice = totalPrice(cartItems);
        cart.setTotalPrices(totalPrice);

        return shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteItemFromCart(Product product, Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItem();
        CartItem item = findCartItem(cartItems, product.getId());

        cartItems.remove(item);
        cartItemRepository.delete(item);

        cart.setCartItem(cartItems);

        int totalItems = totalItems(cartItems);
        cart.setTotalItems(totalItems);

        double totalPrice = totalPrice(cartItems);
        cart.setTotalPrices(totalPrice);

        return shoppingCartRepository.save(cart);
    }

    private CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        CartItem cartItem = null;

        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(Set<CartItem> cartItems) {
        int totalItems = 0;
        for (CartItem item : cartItems) {
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrice(Set<CartItem> cartItems) {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
