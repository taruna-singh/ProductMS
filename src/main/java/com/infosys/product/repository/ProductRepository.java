package com.infosys.product.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.product.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	    public List<Product> findAllBycategory(String category);
	    public List<Product> findAllByproductName(String name);
	    public List<Product> findAllBysellerId(Integer sellerId);
	}

