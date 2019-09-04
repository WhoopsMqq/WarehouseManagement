package com.whoops.accessories.repository;

import com.whoops.accessories.pojo.Accessories;
import com.whoops.accessories.pojo.AccessoriesStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoriesStockRepository extends JpaRepository<AccessoriesStock,Long> {
    AccessoriesStock findByAccessories(Accessories accessories);
}
