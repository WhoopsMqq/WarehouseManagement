package com.whoops.material.repository;

import com.whoops.material.pojo.Material;
import com.whoops.material.pojo.MaterialStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialStockRepository extends JpaRepository<MaterialStock,Long> {
    MaterialStock findByMaterial(Material material);
}
