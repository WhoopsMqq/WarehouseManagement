package com.whoops.product.repository;

import com.whoops.product.pojo.ProductInOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ProductInOutRepository extends JpaRepository<ProductInOut,Long> {

    List<ProductInOut> findByTypeAndCreateTimeBetween(Integer type, Timestamp start, Timestamp end);

}
