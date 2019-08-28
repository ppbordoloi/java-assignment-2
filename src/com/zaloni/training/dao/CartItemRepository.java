package com.zaloni.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zaloni.training.entity.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    @Query(value = "SELECT * FROM cart c WHERE c.user_id = :user_id AND c.product_id = :product_id", nativeQuery = true)
    public List<CartItem> searchByUserIdAndProductId(@Param("user_id") Long userId, @Param("product_id") Long productId);

    @Query(value = "SELECT * FROM cart c WHERE c.user_id = :user_id", nativeQuery = true)
    public List<CartItem> searchByUserId(@Param("user_id") Long userId);
}
