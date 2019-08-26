package com.whoops.product.repository;

import com.whoops.product.pojo.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock,Long> {
}
