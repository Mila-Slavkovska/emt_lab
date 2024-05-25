package mk.ukim.finki.emt.ordermanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.models.enumerations.OrderType;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.OfferId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {
    private Money itemPrice;
    private int quantity;
    private String artist;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    @AttributeOverride(name="id", column = @Column(name = "offer_id", nullable = false))
    private OfferId offerId;

    public OrderItem() {

    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }

    public OrderItem(@NonNull OfferId offerId, @NonNull Money itemPrice, int qty, String artist, OrderType orderType){
        super(DomainObjectId.randomId(OrderItemId.class));
        this.offerId = offerId;
        this.itemPrice = itemPrice;
        this.quantity = qty;
        this.artist = artist;
        this.type = orderType;
    }
}
