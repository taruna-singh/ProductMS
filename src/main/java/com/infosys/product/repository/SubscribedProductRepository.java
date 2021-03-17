package com.infosys.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.product.entity.SubscribedProduct;
@Repository
public interface SubscribedProductRepository extends JpaRepository<SubscribedProduct, Integer> {

}
