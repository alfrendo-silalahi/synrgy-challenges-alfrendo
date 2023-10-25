package com.alfrendo.challenge4.repository;

import com.alfrendo.challenge4.model.Merchant;
import com.alfrendo.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByMerchant(Merchant merchant);

}
