package mk.ukim.finki.emt.ordermanagement.domain.models;

import jakarta.persistence.*;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.models.enumerations.CartState;
import mk.ukim.finki.emt.ordermanagement.domain.models.enumerations.OrderType;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Offer;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart extends AbstractEntity<ShoppingCartId> {
    private double discount;
    private Instant createdOn;
    @Enumerated(EnumType.STRING)
    private CartState state;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList;
    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public ShoppingCart(){
        super(ShoppingCartId.randomId(ShoppingCartId.class));
    }

    public ShoppingCart(Instant now, @NonNull Currency currency){
        super(ShoppingCartId.randomId(ShoppingCartId.class));
        this.createdOn = now;
        this.currency = currency;
        this.discount = 0.2;
    }

    public Money total() {
        Money total = orderItemList.stream()
                .map(OrderItem::subtotal)
                .reduce(new Money(currency, 0), Money::add);

        return total.subtract(calculateDiscount());
    }

    private Money calculateDiscount() {
        Money totalDiscount = new Money(currency, 0);

        Map<String, List<OrderItem>> itemsByArtist = orderItemList.stream()
                .collect(Collectors.groupingBy(OrderItem::getArtist));

        for (Map.Entry<String, List<OrderItem>> entry : itemsByArtist.entrySet()) {
            List<OrderItem> items = entry.getValue();
            boolean hasArtwork = items.stream().anyMatch(item -> item.getType() == OrderType.ArtWork);
            boolean hasTutorial = items.stream().anyMatch(item -> item.getType() == OrderType.Tutorial);

            if (hasArtwork && hasTutorial) {
                for (OrderItem item : items) {
                    if (item.getType() == OrderType.ArtWork || item.getType() == OrderType.Tutorial) {
                        totalDiscount = totalDiscount.add(item.subtotal().multiply(this.discount));
                    }
                }
            }
        }

        return totalDiscount;
    }

    public OrderItem addItem(@NonNull Offer offer, int qty, String artist, OrderType orderType){
        Objects.requireNonNull(offer, "offer must not be null");
        var item = new OrderItem(offer.getId(), offer.getPrice(),qty, artist, orderType);
        orderItemList.add(item);

        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemList.removeIf(v->v.getId().equals(orderItemId));
    }

}
