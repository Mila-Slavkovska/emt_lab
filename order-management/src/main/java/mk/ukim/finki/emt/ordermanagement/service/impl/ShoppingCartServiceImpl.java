package mk.ukim.finki.emt.ordermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.CartIdNotFoundException;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotFoundException;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.ordermanagement.domain.models.ShoppingCartId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.ShoppingCartRepository;
import mk.ukim.finki.emt.ordermanagement.service.ShoppingCartService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final Validator validator;

    @Override
    public ShoppingCartId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null");
        var constraintValidations = validator.validate(orderForm);
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = shoppingCartRepository.saveAndFlush(toDomainObject(orderForm));
//        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<ShoppingCart> findAll() {
        return this.shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(ShoppingCartId id) {
        return this.shoppingCartRepository.findById(id);
    }

    @Override
    public void addItem(ShoppingCartId shoppingCartId, OrderItemForm orderItemForm) throws CartIdNotFoundException {
        ShoppingCart order = shoppingCartRepository.findById(shoppingCartId).orElseThrow(CartIdNotFoundException::new);
        order.addItem(orderItemForm.getOffer(),orderItemForm.getQuantity(), orderItemForm.getArtist(), orderItemForm.getOrderType());
        shoppingCartRepository.saveAndFlush(order);
//        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));

    }

    @Override
    public void deleteItem(ShoppingCartId shoppingCartId, OrderItemId orderItemId) throws CartIdNotFoundException, OrderItemIdNotFoundException {
        ShoppingCart order = shoppingCartRepository.findById(shoppingCartId).orElseThrow(CartIdNotFoundException::new);
        order.removeItem(orderItemId);
        shoppingCartRepository.saveAndFlush(order);
    }

    private ShoppingCart toDomainObject(OrderForm orderForm){
        var order = new ShoppingCart(Instant.now(), orderForm.getCurrency());
        orderForm.getItems().forEach(item -> order.addItem(item.getOffer(), item.getQuantity(), item.getArtist(), item.getOrderType()));
        return order;
    }
}
