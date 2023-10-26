package com.alfrendo.challenge4.repository;

import com.alfrendo.challenge4.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
