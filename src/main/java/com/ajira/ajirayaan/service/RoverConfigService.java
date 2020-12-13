package com.ajira.ajirayaan.service;

import com.ajira.ajirayaan.entity.*;
import com.ajira.ajirayaan.model.*;
import com.ajira.ajirayaan.model.request.Direction;
import com.ajira.ajirayaan.model.request.RoverConfig;
import com.ajira.ajirayaan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * RoverConfigService is for saving all the configurations
 * related to Rover
 */
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

    /**
     * Saving rover configurations
     * @param roverConfig rover configuration JSON
     * @return ERROR | SUCCESS
     */
    public String saveRoverConfigurations(RoverConfig roverConfig){
        if(isRoverConfigurationsExists()){
            return "ERROR";
        }
        saveInventory(roverConfig);
        saveRoverStatus(roverConfig);
        saveStateAndActions(roverConfig);
        savePerform(roverConfig);
        saveScenarios(roverConfig);
        return "SUCCESS";
    }

    /**
     * deleting all the rover configurations
     */
    public void deleteRoverConfigurations(){
        inventoryItemRepository.deleteAll();
        roverStatusRepository.deleteAll();
        stateAndActionRepository.deleteAll();
        performRepository.deleteAll();
        scenariosRepository.deleteAll();
    }

    /**
     * Check if already rover configurations are initiated
     * @return true | false
     */
    public boolean isRoverConfigurationsExists(){
        RoverStatus roverStatus = getRoverStatus();
        if(Objects.nonNull(roverStatus)){
            return true;
        }
        return false;
    }

    /**
     * Saving Inventory items
     * @param roverConfig rover configurations
     */
    public void saveInventory(RoverConfig roverConfig){
        inventoryItemRepository.saveAll(roverConfig.getInventory());
    }

    /**
     * saving Rover status like location and battery remaining
     * @param roverConfig rover configurations
     */
    public void saveRoverStatus(RoverConfig roverConfig){
        RoverStatus roverStatus = new RoverStatus();
        roverStatus.setId(1L);
        roverStatus.setRow(roverConfig.getDeployPoint().getRow());
        roverStatus.setCol(roverConfig.getDeployPoint().getColumn());
        roverStatus.setBattery(roverConfig.getInitialBattery());
        roverStatusRepository.save(roverStatus);
    }

    /**
     * Saving State and Actions
     * @param roverConfig rover configurations
     */
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

    /**
     * Saving Performs
     * @param roverConfig rover configurations
     */
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

    /**
     * Saving Scenarios
     * @param roverConfig rover configurations
     */
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

    /**
     * getting all the inventory items
     * @return list of inventory items
     */
    public List<InventoryItem> getInventoryItems(){
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    /**
     * getting Rover Status like location and battery remaining
     * @return rover status
     */
    public RoverStatus getRoverStatus(){
        return roverStatusRepository.findById(1L).orElse(null);
    }

    /**
     * Update Rover Status based on the given new location
     * @param roverStatus current rover status
     * @param newLocation new location
     * @return updated rover status
     */
    public RoverStatus updateRoverStatus(RoverStatus roverStatus, Location newLocation){
        roverStatus.setRow(newLocation.getRow());
        roverStatus.setCol(newLocation.getColumn());
        roverStatus.setBattery(roverStatus.getBattery()-1);
        roverStatusRepository.save(roverStatus);
        return roverStatus;
    }

    /**
     * Saving Inventory items list
     * @param inventoryItemList inventory items to save
     */
    public void saveInventoryItemsList(List<InventoryItem> inventoryItemList){
        inventoryItemRepository.deleteAll();
        inventoryItemRepository.saveAll(inventoryItemList);
    }

    /**
     * getting all the inventory items present in the rover
     * @return list of items
     */
    public List<InventoryItem> getInventoryItemsList(){
        return (List<InventoryItem>) inventoryItemRepository.findAll();
    }

    /**
     * getting all the scenarios
     * @return list of scenarios
     */
    public List<Scenarios> getScenarios(){
        return (List<Scenarios>) scenariosRepository.findAll();
    }

    /**
     * getting all the performs
     * @return list of performs
     */
    public List<Perform> getPerforms(){
        return (List<Perform>) performRepository.findAll();
    }

    /**
     * updating inventory items, it will remove items with 0 quantity
     * @param inventoryItemList updated inventory items
     */
    public void updateInventoryItems(List<InventoryItem> inventoryItemList){
        inventoryItemList = inventoryItemList.stream()
                .filter(x->x.getQty()!=0)
                .collect(Collectors.toList());
        saveInventoryItemsList(inventoryItemList);
    }

}
