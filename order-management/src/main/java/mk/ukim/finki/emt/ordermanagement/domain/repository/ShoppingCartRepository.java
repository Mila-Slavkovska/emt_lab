package mk.ukim.finki.emt.ordermanagement.domain.repository;

import mk.ukim.finki.emt.ordermanagement.domain.models.ShoppingCart;
import mk.ukim.finki.emt.ordermanagement.domain.models.ShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {
}
