package com.alfrendo.web.repository;

import com.alfrendo.web.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
