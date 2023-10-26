package com.alfrendo.challenge4.repository;

import com.alfrendo.challenge4.model.Merchant;
import com.alfrendo.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByMerchant(Merchant merchant);

    @Query(
            value = "SELECT * FROM products " +
                    "WHERE id = :id AND merchant_id = :merchant_id",
            nativeQuery = true
    )
    Optional<Product> findByMerchantIdAndProductId(
            @Param("merchant_id") UUID merchantId,
            @Param("id") UUID productId
    );

}
