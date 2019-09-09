package com.whoops.material.repository;

import com.whoops.material.pojo.MaterialInOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MaterialInOutRepository extends JpaRepository<MaterialInOut,Long> {

    List<MaterialInOut> findByTypeAndCreateTimeBetween(Integer type, Timestamp start,Timestamp end);

}
