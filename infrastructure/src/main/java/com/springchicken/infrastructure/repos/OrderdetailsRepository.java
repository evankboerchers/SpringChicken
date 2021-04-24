package com.springchicken.infrastructure.repos;

import com.springchicken.infrastructure.entities.OrderdetailsEntity;
import com.springchicken.infrastructure.entities.OrderdetailsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order details entity JPA repository
 */
@Repository
public interface OrderdetailsRepository extends JpaRepository<OrderdetailsEntity, OrderdetailsEntityPK>
{

}
