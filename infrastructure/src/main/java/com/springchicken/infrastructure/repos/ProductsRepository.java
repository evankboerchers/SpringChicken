package com.springchicken.infrastructure.repos;

import com.springchicken.infrastructure.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product entity JPA repository
 */
@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, String>
{

}
