package mk.ukim.finki.emt.offers.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mk.ukim.finki.emt.offers.domain.models.enumerations.TutorialTag;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.util.List;

@Entity
@Table(name = "tutorial")
public class Tutorial extends AbstractEntity<TutorialId> {
    private String title;
    private Money price;
    private List<TutorialTag> tags;
}
