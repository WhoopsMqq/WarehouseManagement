package com.whoops.material.repository;

import com.whoops.material.pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material,Long> {
}
