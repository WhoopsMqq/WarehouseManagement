package com.whoops.product.repository;

import com.whoops.product.pojo.ProductInOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInOutRepository extends JpaRepository<ProductInOut,Long> {

}
