package mk.ukim.finki.emt.offers.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ArtWorkId extends DomainObjectId {
    private ArtWorkId() {
        super(ArtWorkId.randomId(ArtWorkId.class).getId());
    }

    public ArtWorkId(@NonNull String uuid) {
        super(uuid);
    }

    public static ArtWorkId of(String uuid) {
        ArtWorkId p = new ArtWorkId(uuid);
        return p;
    }

}
