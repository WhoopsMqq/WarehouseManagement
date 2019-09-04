package com.whoops.accessories.service;

import com.whoops.accessories.pojo.Accessories;
import com.whoops.accessories.pojo.AccessoriesInOut;
import com.whoops.accessories.pojo.AccessoriesStock;
import com.whoops.accessories.repository.AccessoriesInOutRepository;
import com.whoops.accessories.repository.AccessoriesRepository;
import com.whoops.accessories.repository.AccessoriesStockRepository;
import com.whoops.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AccessoriesInOutService {
    @Autowired
    private AccessoriesInOutRepository accessoriesInOutRepository;

    @Autowired
    private AccessoriesRepository accessoriesRepository;

    @Autowired
    private AccessoriesStockRepository accessoriesStockRepository;

    public List<AccessoriesInOut> loadAllAccessoriesInOut(){
        List<AccessoriesInOut> accessoriesInOutList = accessoriesInOutRepository.findAll();
        accessoriesInOutList.sort(new Comparator<AccessoriesInOut>() {
            @Override
            public int compare(AccessoriesInOut a1, AccessoriesInOut a2) {
                if(a1.getCreateTime().after(a2.getCreateTime())){
                    return -1;
                }else if(a1.getCreateTime().before(a2.getCreateTime())){
                    return 1;
                }
                return 0;
            }
        });
        return accessoriesInOutList;
    }

    public Response saveAccessoriesInOut(AccessoriesInOut accessoriesInOut) {
//        User currentUser = CurrentUser.getUser();
//        if(currentUser == null){
//            return new Response(false,"请先登录,再进行操作!","/login");
//        }

        Accessories accessories = accessoriesRepository.getOne(accessoriesInOut.getAccessories().getId());
        AccessoriesStock accessoriesStock = accessoriesStockRepository.findByAccessories(accessories);
        if(accessoriesInOut.getType() == 1 && accessoriesInOut.getNumber() > accessoriesStock.getNumber()){
            return new Response(false,"对不起,库存不足,请重新核对出库数量!");
        }else if(accessoriesInOut.getType() == 1 && accessoriesInOut.getNumber() <= accessoriesStock.getNumber()){
            accessoriesStock.setNumber(accessoriesStock.getNumber() - accessoriesInOut.getNumber());
            accessoriesStockRepository.save(accessoriesStock);
        }else{
            accessoriesStock.setNumber(accessoriesStock.getNumber() + accessoriesInOut.getNumber());
            accessoriesStockRepository.save(accessoriesStock);
        }

        accessoriesInOut.setAccessories(accessories);
//        productInOut.setUser_id(currentUser.getId());
//        productInOut.setUsername(currentUser.getUsername());
        accessoriesInOut.setType(accessoriesInOut.getType());
        AccessoriesInOut savedAccessoriesInOut = accessoriesInOutRepository.save(accessoriesInOut);
        if(savedAccessoriesInOut != null){
            return new Response(true,"保存成功!");
        }
        return new Response(false,"保存失败!");
    }
}
