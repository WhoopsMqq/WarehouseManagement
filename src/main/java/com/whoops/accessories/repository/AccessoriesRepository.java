package com.whoops.accessories.repository;

import com.whoops.accessories.pojo.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoriesRepository extends JpaRepository<Accessories,Long> {

}
