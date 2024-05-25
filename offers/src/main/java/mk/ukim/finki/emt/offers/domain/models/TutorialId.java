package mk.ukim.finki.emt.offers.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class TutorialId extends DomainObjectId {
    private TutorialId() {
        super(TutorialId.randomId(TutorialId.class).getId());
    }

    public TutorialId(@NonNull String uuid) {
        super(uuid);
    }

    public static TutorialId of(String uuid) {
        TutorialId p = new TutorialId(uuid);
        return p;
    }

}
