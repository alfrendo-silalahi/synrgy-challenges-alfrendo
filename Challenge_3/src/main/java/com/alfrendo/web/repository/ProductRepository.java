package com.alfrendo.web.repository;

import com.alfrendo.web.model.Merchant;
import com.alfrendo.web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByMerchant(Merchant merchant);

}
