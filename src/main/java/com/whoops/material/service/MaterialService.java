package com.whoops.material.service;

import com.whoops.material.pojo.Material;
import com.whoops.material.pojo.MaterialStock;
import com.whoops.material.repository.MaterialRepository;
import com.whoops.material.repository.MaterialStockRepository;
import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductStock;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialStockRepository materialStockRepository;

    public List<Material> loadAllMaterial(){
        return materialRepository.findAll();
    }

    public Response saveMaterial(Material material){
        Material existedMaterial = materialRepository.findByNameAndColorAndSize(material.getName(),material.getColor(),material.getSize());
        if(existedMaterial != null){
            return new Response(false,"已存在该种原材料!");
        }else if(material.getId() != null && material.getId() != 0L){
            Material oldMaterial = materialRepository.getOne(material.getId());
            oldMaterial.setName(material.getName());
            oldMaterial.setSize(material.getSize());
            oldMaterial.setColor(material.getColor());
            Material editedMaterial = materialRepository.save(oldMaterial);
            if(editedMaterial != null){
                return new Response(true,"修改成功!");
            }
            return  new Response(false,"修改失败!");
        }else{
            Material savedMaterial = materialRepository.save(material);
            if(savedMaterial != null){
                materialStockRepository.save(new MaterialStock(savedMaterial,0L));
                return new Response(true,"保存成功!");
            }
            return new Response(false,"保存失败!");
        }
    }

}
