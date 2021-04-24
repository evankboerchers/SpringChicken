package com.springchicken.infrastructure.repos;

import com.springchicken.infrastructure.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Order entity JPA repository
 */
@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer>
{

    /**
     * @param number The customer number
     * @return A collection of order entities for the specified customer
     */
    @Query (value = "SELECT * FROM orders as o WHERE o.customerNumber = :number", nativeQuery = true)
    Collection<OrdersEntity> getOrdersByCustomerNumber(@Param("number") int number);
}
