package com.whoops.product.repository;

import com.whoops.product.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findByNameAndColorAndSize(String name,String color,String size);

}
