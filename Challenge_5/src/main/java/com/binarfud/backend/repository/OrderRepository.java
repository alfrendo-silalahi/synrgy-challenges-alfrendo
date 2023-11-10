package com.binarfud.backend.repository;

import com.binarfud.backend.model.Order;
import com.binarfud.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByUser(User user, Pageable pageable);

}
