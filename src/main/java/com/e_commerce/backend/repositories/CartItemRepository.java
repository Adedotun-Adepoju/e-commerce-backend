package com.e_commerce.backend.repositories;
import com.e_commerce.backend.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findAllByCartId(String cartId);
    Optional<CartItem> findCartItemByCartIdAndProductId(String cartId, String productId);

    @Query("select sum(quantity) FROM CartItem c WHERE c.cart.id = ?1")
    int sumTotalItems(String cartId);
}
