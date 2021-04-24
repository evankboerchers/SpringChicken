package com.springchicken.infrastructure.repos;

import com.springchicken.infrastructure.entities.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer entity JPA repository
 */
@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity, Integer>
{

}
