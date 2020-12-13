package com.ajira.ajirayaan.service;

import com.ajira.ajirayaan.entity.AreaMap;
import com.ajira.ajirayaan.entity.Environment;
import com.ajira.ajirayaan.entity.EnvironmentUpdate;
import com.ajira.ajirayaan.model.request.EnvironmentConfig;
import com.ajira.ajirayaan.model.request.TerrainType;
import com.ajira.ajirayaan.repository.AreaMapRepository;
import com.ajira.ajirayaan.repository.EnvironmentRepository;
import com.ajira.ajirayaan.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * EnvironmentConfigService is for saving all the configurations
 * related to Environment
 */
@Service
public class EnvironmentConfigService {

    @Autowired
    EnvironmentRepository environmentRepository;
    @Autowired
    AreaMapRepository areaMapRepository;

    /**
     * saving environment configurations and also check if already exits
     * @param environmentConfig environment configurations
     * @return ERROR | SUCCESS
     */
    public String saveEnvironmentConfig(EnvironmentConfig environmentConfig){
        if(isEnvironmentConfigurationExists()){
            return "ERROR";
        }
        saveEnvironment(environmentConfig);
        saveAreaMap(environmentConfig.getAreaMap());
        return "SUCCESS";
    }

    /**
     * Saving Environment details
     * @param environmentConfig environment configurations
     */
    public void saveEnvironment(EnvironmentConfig environmentConfig){
        Environment environment = new Environment();
        environment.setId(1L);
        environment.setTemperature(environmentConfig.getTemperature());
        environment.setHumidity(environmentConfig.getHumidity());
        environment.setSolarFlare(environmentConfig.isSolarFlare());
        environment.setStorm(environmentConfig.isStorm());
        environmentRepository.save(environment);
    }

    /**
     * deleting all the environment configurations
     */
    public void deleteEnvironmentConfig(){
        areaMapRepository.deleteAll();
        environmentRepository.deleteAll();
    }

    /**
     * Checks if environment configurations already exists
     * @return true | false
     */
    public boolean isEnvironmentConfigurationExists(){
        Environment environment = getEnvironment();
        return Objects.nonNull(environment);
    }

    /**
     * Saving Area map after converting each row into string format
     * @param areaMap 2D area map
     */
    public void saveAreaMap(List<List<TerrainType>> areaMap){
        areaMapRepository.saveAll(Converter.mapToString(areaMap));
    }

    /**
     * Getting Environment details
     * @return environment
     */
    public Environment getEnvironment(){
        return environmentRepository.findById(1L).orElse(null);
    }

    /**
     * Getting area map after converting string format into 2D area map
     * @return
     */
    public List<List<String>> getAreaMap(){
        return Converter.stringToMap((List<AreaMap>) areaMapRepository.findAll());
    }

    /**
     * getting area map boundaries
     * @return area map row
     */
    public AreaMap getAreaLimits(){
        return ((List<AreaMap>)areaMapRepository.findAll()).get(0);
    }

    /**
     * Updating Environment details separately
     * @param update properties that needs to update
     * @return SUCCESS
     */
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
            environment.setStorm(update.getStorm());
        }
        if(Objects.nonNull(update.getSolarFlare())){
            environment.setSolarFlare(update.getSolarFlare());
        }
        environmentRepository.save(environment);
        return "SUCCESS";
    }
}
