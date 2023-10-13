package com.alfrendo.web.repository;

import com.alfrendo.web.model.Merchant;
import com.alfrendo.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByUser(User user);

}
