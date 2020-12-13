package com.ajira.ajirayaan.service;

import com.ajira.ajirayaan.entity.AreaMap;
import com.ajira.ajirayaan.entity.Environment;
import com.ajira.ajirayaan.entity.EnvironmentUpdate;
import com.ajira.ajirayaan.model.EnvironmentConfig;
import com.ajira.ajirayaan.model.TerrainType;
import com.ajira.ajirayaan.repository.AreaMapRepository;
import com.ajira.ajirayaan.repository.EnvironmentConfigRepository;
import com.ajira.ajirayaan.repository.EnvironmentRepository;
import com.ajira.ajirayaan.repository.InventoryItemRepository;
import com.ajira.ajirayaan.util.Convert;
import com.ajira.ajirayaan.util.ModelToEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EnvironmentConfigService {

    @Autowired
    EnvironmentConfigRepository environmentConfigRepository;
    @Autowired
    EnvironmentRepository environmentRepository;
    @Autowired
    AreaMapRepository areaMapRepository;
    @Autowired
    StatusResponseService statusResponseService;

    public String saveEnvironmentConfig(EnvironmentConfig environmentConfig){
        if(isEnvironmentConfigurationExists()){
            return "ERROR";
        }
        saveEnvironment(environmentConfig);
        saveAreaMap(environmentConfig.getAreaMap());
        return "SUCCESS";
    }

    public void saveEnvironment(EnvironmentConfig environmentConfig){
        Environment environment = new Environment();
        environment.setId(1L);
        environment.setTemperature(environmentConfig.getTemperature());
        environment.setHumidity(environmentConfig.getHumidity());
        environment.setSolarFlare(environmentConfig.isSolarFlare());
        environment.setStorm(environmentConfig.isStorm());
        environmentRepository.save(environment);
    }

    public boolean isEnvironmentConfigurationExists(){
        Environment environment = getEnvironment();
        return Objects.nonNull(environment);
    }

    public void saveAreaMap(List<List<TerrainType>> areaMap){
        areaMapRepository.saveAll(Convert.mapToString(areaMap));
    }

    public Environment getEnvironment(){
        return environmentRepository.findById(1L).orElse(null);
    }

    public List<List<String>> getAreaMap(){
        return Convert.stringToMap((List<AreaMap>) areaMapRepository.findAll());
    }

    public AreaMap getAreaLimits(){
        return ((List<AreaMap>)areaMapRepository.findAll()).get(0);
    }

    public String updateEnvironment(EnvironmentUpdate update) {
        Environment environment = getEnvironment();
        if(Objects.nonNull(update.getTerrain())){
            environment.setTerrain(update.getTerrain());
        }
        if(Objects.nonNull(update.getHumidity())){
            environment.setHumidity(update.getHumidity());
        }
        if(Objects.nonNull(update.getTemperature())){
            environment.setTemperature(update.getTemperature());
        }
        if(Objects.nonNull(update.getStorm()) ){
            if(update.getStorm() && statusResponseService.checkStormShield()){
                return "ERROR";
            }
            environment.setStorm(update.getStorm());
        }
        if(Objects.nonNull(update.getSolarFlare())){
            environment.setSolarFlare(update.getSolarFlare());
        }
        environmentRepository.save(environment);
        statusResponseService.checkScenarios();
        return "SUCCESS";
    }


}
