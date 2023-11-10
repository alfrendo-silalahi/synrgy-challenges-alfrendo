package com.binarfud.backend.repository;

import com.binarfud.backend.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    List<Merchant> findAllByOpen(Boolean open);

}
