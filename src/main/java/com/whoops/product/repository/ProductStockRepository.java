package com.whoops.product.repository;

import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock,Long> {
    ProductStock findByProduct(Product product);
}
