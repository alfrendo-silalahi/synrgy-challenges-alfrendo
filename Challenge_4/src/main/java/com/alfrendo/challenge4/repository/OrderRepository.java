package com.alfrendo.challenge4.repository;

import com.alfrendo.challenge4.model.Order;
import com.alfrendo.challenge4.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByUser(User user, Pageable pageable);

}
