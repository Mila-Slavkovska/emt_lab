package mk.ukim.finki.emt.ordermanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.ordermanagement.domain.models.enumerations.OrderType;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Offer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForm {
    @NotNull
    private Offer offer;

    @Min(1)
    private int quantity;

    private String artist;

    private OrderType orderType;
}
