package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class OfferId extends DomainObjectId {
    protected OfferId() {
        super(OfferId.randomId(OfferId.class).getId());
    }

    public OfferId(String uuid) {
        super(uuid);
    }

}
