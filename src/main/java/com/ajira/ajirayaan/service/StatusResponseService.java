package com.ajira.ajirayaan.service;

import com.ajira.ajirayaan.entity.*;
import com.ajira.ajirayaan.model.InventoryItemType;
import com.ajira.ajirayaan.model.Location;
import com.ajira.ajirayaan.model.Rover;
import com.ajira.ajirayaan.model.Status;
import com.ajira.ajirayaan.repository.PerformRepository;
import com.ajira.ajirayaan.repository.ScenariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class StatusResponseService {

    @Autowired
    RoverConfigService roverConfigService;
    @Autowired
    EnvironmentConfigService environmentConfigService;
    @Autowired
    PerformRepository performRepository;
    @Autowired
    ScenariosRepository scenariosRepository;

    public Status getStatus(){
        Status status = new Status();
        Rover rover = new Rover();
        Environment environment = getEnvironment();
        RoverStatus roverStatus = roverConfigService.getRoverStatus();
        rover.setBattery(roverStatus.getBattery());
        rover.setLocation(new Location(roverStatus.getRow(), roverStatus.getCol()));
        rover.setInventory(roverConfigService.getInventoryItems());
        status.setEnvironment(environment);
        status.setRover(rover);
        return status;
    }

    public AreaMap getAreaLimits(){
        return environmentConfigService.getAreaLimits();
    }

    public Environment getEnvironment(){
        return environmentConfigService.getEnvironment();
    }

    public boolean checkStormShield(){
        List<InventoryItem> inventoryItemList = roverConfigService.getInventoryItems();
        long count = inventoryItemList.stream()
                .filter(inventoryItem -> inventoryItem.equals(InventoryItemType.STORM_SHIELD))
                .count();
        if (count>0){
            return true;
        }
        return false;
    }
    public void updateEnvironmentTerrain(Location location){
        List<List<String>> areaMap = environmentConfigService.getAreaMap();
        EnvironmentUpdate environmentUpdate = new EnvironmentUpdate();
        environmentUpdate.setTerrain(areaMap.get(location.getRow()-1).get(location.getColumn()-1));
        environmentConfigService.updateEnvironment(environmentUpdate);
    }

    public boolean checkScenarios(){
        AtomicBoolean response = new AtomicBoolean(true);
        Map<String, Perform> performMap = ((List<Perform>) performRepository.findAll()).stream()
                .collect(Collectors.toMap(x->x.getScenario(), x->x));
        List<Scenarios> scenariosList = (List<Scenarios>)scenariosRepository.findAll();
        scenariosList.forEach(scenarios -> {
            if(checkCondition(scenarios)){
                Perform perform = performMap.get(scenarios.getScenario());
                response.set(doPerform(perform));
            }
        });
        return response.get();
    }

    public void updateInventoryItems(List<InventoryItem> inventoryItemList){
        inventoryItemList = inventoryItemList.stream()
                .filter(x->x.getQty()!=0)
                .collect(Collectors.toList());
        roverConfigService.saveInventoryItemsList(inventoryItemList);
    }

    public boolean doPerform(Perform perform){
        Map<InventoryItemType,InventoryItem> inventoryItemMap = roverConfigService.getInventoryItemsList()
                .stream()
                .collect(Collectors.toMap(InventoryItem::getType, x->x));
        InventoryItem inventoryItem = new InventoryItem();
        if(perform.getName().equals("COLLECT-SAMPLE")){
            if(inventoryItemMap.containsKey(perform.getType())){
                inventoryItem = inventoryItemMap.get(perform.getType());
                inventoryItem.setQty(inventoryItem.getQty()+perform.getQty());
                inventoryItem.setPriority(inventoryItem.getQty());
            }else{
                inventoryItem.setType(InventoryItemType.WATER_SAMPLE);
                inventoryItem.setQty(perform.getQty());
                inventoryItem.setPriority(inventoryItem.getQty());
            }
        }
        if(perform.getName().equalsIgnoreCase("ITEM-USAGE")){
            if(inventoryItemMap.containsKey(perform.getType())){
                inventoryItem = inventoryItemMap.get(perform.getType());
                inventoryItem.setQty(inventoryItem.getQty()-perform.getQty());
            }else{
                return false;
            }
        }
        inventoryItemMap.put(inventoryItem.getType(),inventoryItem);
        updateInventoryItems(inventoryItemMap.values().stream().collect(Collectors.toList()));
        return true;
    }


    public boolean checkCondition(Scenarios scenarios){
        Status status = getStatus();
        switch (scenarios.getProperty()){
            case "battery":
                return status.getRover().getBattery()==Integer.parseInt(scenarios.getValue());
            case "terrain":
                return status.getEnvironment().getTerrain().equalsIgnoreCase(scenarios.getValue());
            case "storm":
                return status.getEnvironment().isStorm() == Boolean.parseBoolean(scenarios.getValue());
        }
        return false;
    }

}
