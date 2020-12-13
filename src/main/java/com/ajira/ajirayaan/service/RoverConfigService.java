package com.ajira.ajirayaan.service;

import com.ajira.ajirayaan.entity.*;
import com.ajira.ajirayaan.model.*;
import com.ajira.ajirayaan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class RoverConfigService {

    @Autowired
    InventoryItemRepository inventoryItemRepository;
    @Autowired
    RoverStatusRepository roverStatusRepository;
    @Autowired
    StateAndActionRepository stateAndActionRepository;
    @Autowired
    PerformRepository performRepository;
    @Autowired
    ScenariosRepository scenariosRepository;
    @Autowired
    StatusResponseService statusResponseService;


    public String saveRoverConfigurations(RoverConfig roverConfig){
        if(isRoverConfigurationsExists()){
            return "ERROR";
        }
        saveInventory(roverConfig);
        saveRoverStatus(roverConfig);
        saveRoverStatus(roverConfig);
        saveStateAndActions(roverConfig);
        savePerform(roverConfig);
        saveScenarios(roverConfig);
        return "SUCCESS";
    }

    public boolean isRoverConfigurationsExists(){
        RoverStatus roverStatus = getRoverStatus();
        if(Objects.nonNull(roverStatus)){
            return true;
        }
        return false;
    }

    public void saveInventory(RoverConfig roverConfig){
        inventoryItemRepository.saveAll(roverConfig.getInventory());
    }

    public void saveRoverStatus(RoverConfig roverConfig){
        RoverStatus roverStatus = new RoverStatus();
        roverStatus.setId(1L);
        roverStatus.setRow(roverConfig.getDeployPoint().getRow());
        roverStatus.setCol(roverConfig.getDeployPoint().getColumn());
        roverStatus.setBattery(roverConfig.getInitialBattery());
        statusResponseService.updateEnvironmentTerrain(new Location(roverStatus.getRow(),roverStatus.getCol()));
        roverStatusRepository.save(roverStatus);
    }

    public void saveStateAndActions(RoverConfig roverConfig){
        List<StateAndAction> stateAndActionList = new ArrayList<>();
        roverConfig.getStates().forEach(state->{
            StateAndAction stateAndAction = new StateAndAction();
            state.getAllowedActions().forEach(action -> {
                stateAndAction.setName(state.getName());
                stateAndAction.setAction(action);
                stateAndActionList.add(stateAndAction);
            });
        });
        stateAndActionRepository.saveAll(stateAndActionList);
    }

    public void savePerform(RoverConfig roverConfig){
        List<Perform> performList = new ArrayList<>();
        roverConfig.getScenarios().forEach(scenario -> {
            scenario.getRover().forEach(roverScenario -> {
                Perform perform = new Perform();
                perform.setScenario(scenario.getName());
                perform.setRoverIs(roverScenario.getIs());
                if(roverScenario.getPerforms()!=null && roverScenario.getPerforms().getCollectSample()!=null){
                    perform.setName("COLLECT-SAMPLE");
                    perform.setType(roverScenario.getPerforms().getCollectSample().getType());
                    perform.setQty(roverScenario.getPerforms().getCollectSample().getQty());
                }
                if(roverScenario.getPerforms()!=null && roverScenario.getPerforms().getItemUsage()!=null){
                    perform.setName("ITEM-USAGE");
                    perform.setType(roverScenario.getPerforms().getItemUsage().getType());
                    perform.setQty(roverScenario.getPerforms().getItemUsage().getQty());
                }
                performList.add(perform);
            });
        });
        performRepository.saveAll(performList);
    }

    public void saveScenarios(RoverConfig roverConfig){
        List<Scenarios> scenariosList = new ArrayList<>();
        roverConfig.getScenarios().forEach(scenario -> {
            scenario.getConditions().forEach(scenarioCondition -> {
                Scenarios scenarios = new Scenarios();
                scenarios.setScenario(scenario.getName());
                scenarios.setType(scenarioCondition.getType());
                scenarios.setProperty(scenarioCondition.getProperty());
                scenarios.setOperator(scenarioCondition.getOperator());
                scenarios.setValue(scenarioCondition.getValue());
                scenariosList.add(scenarios);
            });
        });
        scenariosRepository.saveAll(scenariosList);
    }

    public RoverStatus getRoverStatus(){
        return roverStatusRepository.findById(1L).orElse(null);
    }

    public List<InventoryItem> getInventoryItems(){
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    public String makeMove(Direction direction){
        RoverStatus roverStatus = getRoverStatus();
        Location currentLocation = new Location(roverStatus.getRow(), roverStatus.getCol());
        Location locationAfterMove = getLocationAfterMove(direction,currentLocation);
        String response = checkEnvironmentCondition();
        if(response.equals("SUCCESS")){
            response = checkAreaLimits(locationAfterMove);
            if(response.equals("SUCCESS")){
                roverStatus = updateLocation(roverStatus,locationAfterMove);
                statusResponseService.updateEnvironmentTerrain(locationAfterMove);
                statusResponseService.checkScenarios();
                response = checkBatteryCondition(roverStatus);
            }
        }
        return response;
    }

    public String checkBatteryCondition(RoverStatus roverStatus){
        if(roverStatus.getBattery()<=0){
            return "ERROR";
        }
        return "SUCCESS";
    }



    public RoverStatus updateLocation(RoverStatus roverStatus, Location newLocation){
        roverStatus.setRow(newLocation.getRow());
        roverStatus.setCol(newLocation.getColumn());
        roverStatus.setBattery(roverStatus.getBattery()-1);
        roverStatusRepository.save(roverStatus);
        return roverStatus;
    }


    public void saveInventoryItemsList(List<InventoryItem> inventoryItemList){
        inventoryItemRepository.deleteAll();
        inventoryItemRepository.saveAll(inventoryItemList);
    }
    public List<InventoryItem> getInventoryItemsList(){
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    public String checkEnvironmentCondition(){
        Environment environment = statusResponseService.getEnvironment();
        if(environment.isStorm()){
            return "Cannot move during a storm";
        }
        return "SUCCESS";
    }

    public String checkAreaLimits(Location locationAfterMove){
        AreaMap areaMapLimits = statusResponseService.getAreaLimits();
        if(locationAfterMove.getColumn() > areaMapLimits.getColLimit() ||
        locationAfterMove.getRow() > areaMapLimits.getRowLimit()){
            return "Can move only within mapped area";
        }
        return "SUCCESS";
    }

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

    public Location moveUp(Location currentLocation){
        currentLocation.setRow(currentLocation.getRow()-1);
        return currentLocation;
    }

    public Location moveDown(Location currentLocation){
        currentLocation.setRow(currentLocation.getRow()+1);
        return currentLocation;
    }

    public Location moveLeft(Location currentLocation){
        currentLocation.setColumn(currentLocation.getColumn()-1);
        return currentLocation;
    }

    public Location moveRight(Location currentLocation){
        currentLocation.setColumn(currentLocation.getColumn()+1);
        return currentLocation;
    }

}
