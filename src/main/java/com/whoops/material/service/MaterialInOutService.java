package com.whoops.material.service;

import com.whoops.material.pojo.Material;
import com.whoops.material.pojo.MaterialInOut;
import com.whoops.material.pojo.MaterialStock;
import com.whoops.material.repository.MaterialInOutRepository;
import com.whoops.material.repository.MaterialRepository;
import com.whoops.material.repository.MaterialStockRepository;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MaterialInOutService {
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private MaterialInOutRepository materialInOutRepository;
    @Autowired
    private MaterialStockRepository materialStockRepository;

    public List<MaterialInOut> loadAllMaterialInOut(){
        List<MaterialInOut> materialInOutList = materialInOutRepository.findAll();
        materialInOutList.sort(new Comparator<MaterialInOut>() {
            @Override
            public int compare(MaterialInOut m1, MaterialInOut m2) {
                if(m1.getCreateTime().after(m2.getCreateTime())){
                    return -1;
                }else if(m1.getCreateTime().before(m2.getCreateTime())){
                    return 1;
                }
                return 0;
            }
        });
        return materialInOutList;
    }

    public Response saveMaterialInOut(MaterialInOut materialInOut) {
//        User currentUser = CurrentUser.getUser();
//        if(currentUser == null){
//            return new Response(false,"请先登录,再进行操作!","/login");
//        }

        Material material = materialRepository.getOne(materialInOut.getMaterial().getId());
        MaterialStock materialStock = materialStockRepository.findByMaterial(material);
        if(materialInOut.getType() == 1 && materialInOut.getNumber() > materialStock.getNumber()){
            return new Response(false,"对不起,库存不足,请重新核对出库数量!");
        }else if(materialInOut.getType() == 1 && materialInOut.getNumber() <= materialStock.getNumber()){
            materialStock.setNumber(materialStock.getNumber() - materialInOut.getNumber());
            materialStockRepository.save(materialStock);
        }else{
            materialStock.setNumber(materialStock.getNumber() + materialInOut.getNumber());
            materialStockRepository.save(materialStock);
        }

        materialInOut.setMaterial(material);
//        productInOut.setUser_id(currentUser.getId());
//        productInOut.setUsername(currentUser.getUsername());
        materialInOut.setType(materialInOut.getType());
        MaterialInOut savedMaterialInOut = materialInOutRepository.save(materialInOut);
        if(savedMaterialInOut != null){
            return new Response(true,"保存成功!");
        }
        return new Response(false,"保存失败!");
    }
}
