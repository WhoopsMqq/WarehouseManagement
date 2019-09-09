package com.whoops.analysis.service;

import com.whoops.accessories.pojo.AccessoriesInOut;
import com.whoops.accessories.repository.AccessoriesInOutRepository;
import com.whoops.analysis.pojo.Analysis;
import com.whoops.material.pojo.MaterialInOut;
import com.whoops.material.repository.MaterialInOutRepository;
import com.whoops.product.pojo.ProductInOut;
import com.whoops.product.repository.ProductInOutRepository;
import javafx.scene.input.DataFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AnalysisService {
    @Autowired
    private ProductInOutRepository productInOutRepository;
    @Autowired
    private MaterialInOutRepository materialInOutRepository;
    @Autowired
    private AccessoriesInOutRepository accessoriesInOutRepository;


    public List<Analysis> loadAnalysis(String startTime,String endTime, Integer kind){
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp start;
        Timestamp end;
        if (StringUtils.isEmpty(endTime)){
            end = Timestamp.valueOf(dataFormat.format(new Date()));
        }else{
            end = Timestamp.valueOf(endTime);
        }
        if (StringUtils.isEmpty(startTime)){
            start = new Timestamp((new Date()).getTime() - 7*24*60*60*1000);
        }else{
            start = Timestamp.valueOf(startTime);
        }
        if (kind == null || kind == 0){
            kind = 3;
        }
        return getAnalysisList(start,end,kind);
    }

    private List<Analysis> getAnalysisList(Timestamp start,Timestamp end,Integer kind){
        List<Analysis> analysisList = new ArrayList<>();
        if (kind == 3){//成品
            //出库记录
            List<ProductInOut> productOutList = productInOutRepository.findByTypeAndCreateTimeBetween(1,start,end);
            Map<Long,List<ProductInOut>> productOutMap = productListToMap(productOutList);
            analysisList.addAll(productAnalysis(productOutMap,analysisList.size()+1));

            //入库记录
            List<ProductInOut> productInList = productInOutRepository.findByTypeAndCreateTimeBetween(2,start,end);
            Map<Long,List<ProductInOut>> productInMap = productListToMap(productInList);
            analysisList.addAll(productAnalysis(productInMap,analysisList.size()+1));
        }else if (kind == 2){//配件
            //出库记录
            List<AccessoriesInOut> accessoriesOutList = accessoriesInOutRepository.findByTypeAndCreateTimeBetween(1,start,end);
            Map<Long,List<AccessoriesInOut>> accessoriesOutMap = accessoriesListToMap(accessoriesOutList);
            analysisList.addAll(accessoriesAnalysis(accessoriesOutMap,analysisList.size()+1));

            //入库记录
            List<AccessoriesInOut> accessoriesInList = accessoriesInOutRepository.findByTypeAndCreateTimeBetween(2,start,end);
            Map<Long,List<AccessoriesInOut>> accessoriesInMap = accessoriesListToMap(accessoriesInList);
            analysisList.addAll(accessoriesAnalysis(accessoriesInMap,analysisList.size()+1));
        }else{//原材料
            //出库记录
            List<MaterialInOut> materialOutList = materialInOutRepository.findByTypeAndCreateTimeBetween(1,start,end);
            Map<Long,List<MaterialInOut>> materialOutMap = materialListToMap(materialOutList);
            analysisList.addAll(materialAnalysis(materialOutMap,analysisList.size()+1));

            //入库记录
            List<MaterialInOut> materialInList = materialInOutRepository.findByTypeAndCreateTimeBetween(2,start,end);
            Map<Long,List<MaterialInOut>> materialInMap = materialListToMap(materialInList);
            analysisList.addAll(materialAnalysis(materialInMap,analysisList.size()+1));
        }
        return analysisList;
    }

    private Map<Long,List<ProductInOut>> productListToMap(List<ProductInOut> productInOutList){
        Map<Long,List<ProductInOut>> map = new HashMap<>();
        for (ProductInOut productInOut : productInOutList){
            if (map.containsKey(productInOut.getProduct().getId())){
                map.get(productInOut.getProduct().getId()).add(productInOut);
            }else{
                List<ProductInOut> list = new ArrayList<>();
                list.add(productInOut);
                map.put(productInOut.getProduct().getId(),list);
            }
        }
        return map;
    }

    private Map<Long,List<AccessoriesInOut>> accessoriesListToMap(List<AccessoriesInOut> accessoriesInOutList){
        Map<Long,List<AccessoriesInOut>> map = new HashMap<>();
        for (AccessoriesInOut accessoriesInOut : accessoriesInOutList){
            if (map.containsKey(accessoriesInOut.getAccessories().getId())){
                map.get(accessoriesInOut.getAccessories().getId()).add(accessoriesInOut);
            }else{
                List<AccessoriesInOut> list = new ArrayList<>();
                list.add(accessoriesInOut);
                map.put(accessoriesInOut.getAccessories().getId(),list);
            }
        }
        return map;
    }

    private Map<Long,List<MaterialInOut>> materialListToMap(List<MaterialInOut> materialInOutList){
        Map<Long,List<MaterialInOut>> map = new HashMap<>();
        for (MaterialInOut materialInOut : materialInOutList){
            if (map.containsKey(materialInOut.getMaterial().getId())){
                map.get(materialInOut.getMaterial().getId()).add(materialInOut);
            }else{
                List<MaterialInOut> list = new ArrayList<>();
                list.add(materialInOut);
                map.put(materialInOut.getMaterial().getId(),list);
            }
        }
        return map;
    }

    private List<Analysis> productAnalysis(Map<Long,List<ProductInOut>> map,Integer index){
        List<Analysis> analysisList = new ArrayList<>();
        for(Long id : map.keySet()){
            List<ProductInOut> list = map.get(id);
            Analysis analysis = new Analysis(index,list.get(0).getProductName(),list.get(0).getTypeName(),0L,0.0);
            for(ProductInOut productInOut : list){
                analysis.setNumber(analysis.getNumber() + (productInOut.getNumber() == null ? 0:productInOut.getNumber()));
            }
            index++;
            analysisList.add(analysis);
        }
        return analysisList;
    }

    private List<Analysis> materialAnalysis(Map<Long,List<MaterialInOut>> map,Integer index){
        List<Analysis> analysisList = new ArrayList<>();
        for(Long id : map.keySet()){
            List<MaterialInOut> list = map.get(id);
            Analysis analysis = new Analysis(index,list.get(0).getMaterialName(),list.get(0).getTypeName(),0L,0.0);
            for(MaterialInOut materialInOut : list){
                analysis.setNumber(analysis.getNumber() + (materialInOut.getNumber() == null ? 0:materialInOut.getNumber()));
                analysis.setTotalPrice(analysis.getTotalPrice() + (materialInOut.getTotalPrice() == null ? 0.0:materialInOut.getTotalPrice()));
            }
            index++;
            analysisList.add(analysis);
        }
        return analysisList;
    }

    private List<Analysis> accessoriesAnalysis(Map<Long,List<AccessoriesInOut>> map,Integer index){
        List<Analysis> analysisList = new ArrayList<>();
        for(Long id : map.keySet()){
            List<AccessoriesInOut> list = map.get(id);
            Analysis analysis = new Analysis(index,list.get(0).getAccessoriesName(),list.get(0).getTypeName(),0L,0.0);
            for(AccessoriesInOut accessoriesInOut : list){
                analysis.setNumber(analysis.getNumber() + (accessoriesInOut.getNumber() == null ? 0:accessoriesInOut.getNumber()));
                analysis.setTotalPrice(analysis.getTotalPrice() + (accessoriesInOut.getTotalPrice() == null ? 0.0:accessoriesInOut.getTotalPrice()));
            }
            index++;
            analysisList.add(analysis);
        }
        return analysisList;
    }





}
