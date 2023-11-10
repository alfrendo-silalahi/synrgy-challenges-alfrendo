package com.binarfud.backend.repository;

import com.binarfud.backend.model.Merchant;
import com.binarfud.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findAllByMerchant(Merchant merchant, Pageable pageable);

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
