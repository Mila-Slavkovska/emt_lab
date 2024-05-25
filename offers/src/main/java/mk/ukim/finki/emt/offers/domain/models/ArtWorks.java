package mk.ukim.finki.emt.offers.domain.models;

import jakarta.persistence.*;
import mk.ukim.finki.emt.offers.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.util.List;

@Entity
@Table(name = "artwork")
public class ArtWorks extends AbstractEntity<ArtWorkId> {

    private String title;
    private String description;
    private Quantity quantity;
    private List<String> pictures;
    private Money price;
}
