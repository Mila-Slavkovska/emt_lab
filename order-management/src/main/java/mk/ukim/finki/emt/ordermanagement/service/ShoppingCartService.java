package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.exceptions.CartIdNotFoundException;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotFoundException;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.ordermanagement.domain.models.ShoppingCartId;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCartId placeOrder(OrderForm orderForm);
    List<ShoppingCart> findAll();
    Optional<ShoppingCart> findById(ShoppingCartId id);
    void addItem(ShoppingCartId shoppingCartId, OrderItemForm orderItemForm) throws CartIdNotFoundException;
    void deleteItem(ShoppingCartId shoppingCartId, OrderItemId orderItemId) throws CartIdNotFoundException, OrderItemIdNotFoundException;
}
