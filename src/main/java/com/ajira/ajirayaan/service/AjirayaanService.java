package com.ajira.ajirayaan.service;

import com.ajira.ajirayaan.entity.*;
import com.ajira.ajirayaan.model.request.Direction;
import com.ajira.ajirayaan.model.request.EnvironmentConfig;
import com.ajira.ajirayaan.model.request.InventoryItemType;
import com.ajira.ajirayaan.model.Location;
import com.ajira.ajirayaan.model.Rover;
import com.ajira.ajirayaan.model.Status;
import com.ajira.ajirayaan.model.request.RoverConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ajirayaan Service is connector for both Rover controller/ Rover config service,
 * Environment controller / Environment config service
 *
 */
@Service
public class AjirayaanService {

    @Autowired
    RoverConfigService roverConfigService;
    @Autowired
    EnvironmentConfigService environmentConfigService;

    /**
     * Saving Rover Configurations and updates Terrain in Environment
     * @param roverConfig rover configurations
     * @return SUCCESS | ERROR
     */
    public String saveRoverConfigurations(RoverConfig roverConfig){
        updateEnvironmentTerrain(new Location(roverConfig.getDeployPoint().getRow(),
                roverConfig.getDeployPoint().getColumn()));
        return roverConfigService.saveRoverConfigurations(roverConfig);
    }

    /**
     * Saving Environment Configurations
     * @param environmentConfig environment configurations
     * @return SUCCESS | ERROR
     */
    public String saveEnvironmentConfig(EnvironmentConfig environmentConfig){
        return environmentConfigService.saveEnvironmentConfig(environmentConfig);
    }

    /**
     * Getting Environment and Rover Details and forming Status
     * @return status
     */
    public Status getStatus(){
        Status status = new Status();
        Rover rover = new Rover();
        Environment environment = environmentConfigService.getEnvironment();
        RoverStatus roverStatus = roverConfigService.getRoverStatus();
        rover.setBattery(roverStatus.getBattery());
        rover.setLocation(new Location(roverStatus.getRow(), roverStatus.getCol()));
        rover.setInventory(roverConfigService.getInventoryItems());
        status.setEnvironment(environment);
        status.setRover(rover);
        return status;
    }

    /**
     * Updating Terrain in Environment based on new location details
     * @param location new location
     */
    public void updateEnvironmentTerrain(Location location){
        List<List<String>> areaMap = environmentConfigService.getAreaMap();
        EnvironmentUpdate environmentUpdate = new EnvironmentUpdate();
        environmentUpdate.setTerrain(areaMap.get(location.getRow()-1).get(location.getColumn()-1));
        updateEnvironment(environmentUpdate);
    }

    /**
     * Updating Environment specific to property
     * @param environmentUpdate properties needs to update
     * @return SUCCESS | ERROR
     */
    public String updateEnvironment(EnvironmentUpdate environmentUpdate){
        String response = environmentConfigService.updateEnvironment(environmentUpdate);
        if(!checkScenarios("STORM")){
            response = "ERROR";
        }
        return response;
    }

    /**
     * Checking all the scenarios that will meet
     * @param action will contain either STORM or MOVE
     * @return true | false
     */
    public boolean checkScenarios(String action){
        boolean response = true;
        Map<String, Perform> performMap = roverConfigService.getPerforms().stream()
                .collect(Collectors.toMap(Perform::getScenario, x->x));
        List<Scenarios> scenariosList = roverConfigService.getScenarios();
        for (Scenarios scenarios : scenariosList) {
            if (checkCondition(scenarios, action)) {
                Perform perform = performMap.get(scenarios.getScenario());
                response = doPerform(perform);
                break;
            }
        }
        return response;
    }

    /**
     * Based on met condition, performing actions
     * @param perform things to do
     * @return true | false
     */
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
        roverConfigService.updateInventoryItems(inventoryItemMap.values().stream().collect(Collectors.toList()));
        return true;
    }


    /**
     * Checking conditions
     * @param scenarios Scenarios
     * @param action action
     * @return true | false
     */
    public boolean checkCondition(Scenarios scenarios, String action){
        Status status = getStatus();
        switch (scenarios.getProperty()){
            case "battery":
                return status.getRover().getBattery()==Integer.parseInt(scenarios.getValue());
            case "storm":
                return status.getEnvironment().isStorm() == Boolean.parseBoolean(scenarios.getValue());
            case "terrain":
                return status.getEnvironment().getTerrain().equalsIgnoreCase(scenarios.getValue()) &&
                        action.equals("MOVE");
            }
        return false;
    }

    /**
     * To make rover move based on given direction
     * @param direction given direction
     * @return SUCCESS | ERROR
     */
    public String makeMove(Direction direction){
        RoverStatus roverStatus = roverConfigService.getRoverStatus();
        Location currentLocation = new Location(roverStatus.getRow(), roverStatus.getCol());
        Location locationAfterMove = getLocationAfterMove(direction,currentLocation);
        String response = checkEnvironmentCondition();
        if(response.equals("SUCCESS")){
            response = checkAreaLimits(locationAfterMove);
            if(response.equals("SUCCESS")){
                roverStatus = roverConfigService.updateRoverStatus(roverStatus,locationAfterMove);
                updateEnvironmentTerrain(locationAfterMove);
                checkScenarios("MOVE");
                response = checkBatteryCondition(roverStatus);
            }
        }
        return response;
    }

    /**
     * Check battery condition if it reaches 0
     * @param roverStatus rover status
     * @return SUCCESS | ERROR
     */
    public String checkBatteryCondition(RoverStatus roverStatus){
        if(roverStatus.getBattery()<=0){
            return "ERROR";
        }
        return "SUCCESS";
    }

    /**
     * Checking Environment condition is storm
     * @return ERROR message or SUCCESS
     */
    public String checkEnvironmentCondition(){
        Environment environment = environmentConfigService.getEnvironment();
        if(environment.isStorm()){
            return "Cannot move during a storm";
        }
        return "SUCCESS";
    }

    /**
     * Checking area boundaries
     * @param locationAfterMove current position after move
     * @return ERROR message or SUCCESS
     */
    public String checkAreaLimits(Location locationAfterMove){
        AreaMap areaMapLimits = environmentConfigService.getAreaLimits();
        if(locationAfterMove.getColumn() > areaMapLimits.getColLimit() ||
                locationAfterMove.getRow() > areaMapLimits.getRowLimit()){
            return "Can move only within mapped area";
        }
        return "SUCCESS";
    }

    /**
     * Getting new location after making a move
     * @param direction direction to move
     * @param currentLocation current position
     * @return new position
     */
    public Location getLocationAfterMove(Direction direction, Location currentLocation){
        Location locationAfterMove = currentLocation;
        switch (direction){
            case UP:
                locationAfterMove = moveUp(currentLocation);
                break;
            case DOWN:
                locationAfterMove = moveDown(currentLocation);
                break;
            case LEFT:
                locationAfterMove = moveLeft(currentLocation);
                break;
            case RIGHT:
                locationAfterMove = moveRight(currentLocation);
        }
        return locationAfterMove;
    }

    /**
     * new position after moving up
     * @param currentLocation current position
     * @return new position
     */
    public Location moveUp(Location currentLocation){
        currentLocation.setRow(currentLocation.getRow()-1);
        return currentLocation;
    }

    /**
     * new position after moving down
     * @param currentLocation current position
     * @return new position
     */
    public Location moveDown(Location currentLocation){
        currentLocation.setRow(currentLocation.getRow()+1);
        return currentLocation;
    }

    /**
     * new position after moving left
     * @param currentLocation current position
     * @return new position
     */
    public Location moveLeft(Location currentLocation){
        currentLocation.setColumn(currentLocation.getColumn()-1);
        return currentLocation;
    }

    /**
     * new position after moving right
     * @param currentLocation current position
     * @return new position
     */
    public Location moveRight(Location currentLocation){
        currentLocation.setColumn(currentLocation.getColumn()+1);
        return currentLocation;
    }

    /**
     * Flushing Rover Configurations
     */
    public void flushRoverConfiguration(){
        roverConfigService.deleteRoverConfigurations();
    }

    /**
     * Flushing Environment Configurations
     */
    public void flushEnvironmentConfiguration(){
        environmentConfigService.deleteEnvironmentConfig();
    }

}
