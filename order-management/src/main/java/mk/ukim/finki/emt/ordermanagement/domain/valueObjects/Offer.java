package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Offer implements ValueObject {
    private final OfferId id;
    private final String name;
    private final Money price;

    private Offer(){
        this.id = OfferId.randomId(OfferId.class);
        this.name = "";
        this.price = Money.valueOf(Currency.MKD, 0);
    }

    @JsonCreator
    public Offer(OfferId id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
