package com.whoops.accessories.repository;

import com.whoops.accessories.pojo.AccessoriesInOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AccessoriesInOutRepository extends JpaRepository<AccessoriesInOut,Long> {

    List<AccessoriesInOut> findByTypeAndCreateTimeBetween(Integer type,Timestamp start,Timestamp end);
}
