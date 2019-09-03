package com.whoops.accessories.service;

import com.whoops.accessories.pojo.Accessories;
import com.whoops.accessories.pojo.AccessoriesStock;
import com.whoops.accessories.repository.AccessoriesRepository;
import com.whoops.accessories.repository.AccessoriesStockRepository;
import com.whoops.product.pojo.Product;
import com.whoops.product.pojo.ProductStock;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoriesService {
    @Autowired
    private AccessoriesRepository accessoriesRepository;
    @Autowired
    private AccessoriesStockRepository accessoriesStockRepository;

    public List<Accessories> loadAllAccessories(){
        return accessoriesRepository.findAll();
    }

    public Response saveAccessories(Accessories accessories){
        Accessories existedAccessories = accessoriesRepository.findByName(accessories.getName());
        if(existedAccessories != null){
            return new Response(false,"已存在该种成品!");
        }else if(accessories.getId() != null && accessories.getId() != 0L){
            Accessories oldAccessories = accessoriesRepository.getOne(accessories.getId());
            oldAccessories.setName(accessories.getName());
            Accessories editedAccessories = accessoriesRepository.save(oldAccessories);
            if(editedAccessories != null){
                return new Response(true,"修改成功!");
            }
            return  new Response(false,"修改失败!");
        }else{
            Accessories savedAccessories = accessoriesRepository.save(accessories);
            if(savedAccessories != null){
                accessoriesStockRepository.save(new AccessoriesStock(savedAccessories,0L));
                return new Response(true,"保存成功!");
            }
            return new Response(false,"保存失败!");
        }
    }


}
