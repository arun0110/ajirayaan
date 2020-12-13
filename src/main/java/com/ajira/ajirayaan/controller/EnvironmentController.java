package com.ajira.ajirayaan.controller;

import com.ajira.ajirayaan.entity.EnvironmentUpdate;
import com.ajira.ajirayaan.model.request.EnvironmentConfig;
import com.ajira.ajirayaan.model.Response;
import com.ajira.ajirayaan.service.AjirayaanService;
import com.ajira.ajirayaan.service.EnvironmentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Environment Controller is to handle requests related to
 * Environment configuration and configuration values updates
 *
 */
@RestController
@RequestMapping("api/environment")
public class EnvironmentController {

    @Autowired
    AjirayaanService ajirayaanService;

    /**
     * Test end point to check server is up or not
     * @return if server is active return Its up message
     */
    @GetMapping("ping")
    public String ping(){
        return "its Up!";
    }

    /**
     * Post mapping for Configuring Initial set up for Environment
     * @param environmentConfig as a request
     * @return response as Http error messages
     */
    @PostMapping(value = "configure", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> environmentConfiguration(@RequestBody EnvironmentConfig environmentConfig){
        String response = ajirayaanService.saveEnvironmentConfig(environmentConfig);
        if(response.equals("ERROR")){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Patch mapping for updating specified values alone
     * @param update specific value to be updated
     * @return response as Http error messages
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateEnvironment(@RequestBody EnvironmentUpdate update){
        String resMsg = ajirayaanService.updateEnvironment(update);
        Response response = new Response();
        response.setMessage(resMsg);
        if(resMsg.equals("ERROR")){
            return new ResponseEntity<Response>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get Mapping to flush Environment configurations for test purpose
     * And also In case if we want to save different environment
     * @return response as Http error messages
     */
    @GetMapping("flush")
    public ResponseEntity<String> flush(){
        ajirayaanService.flushEnvironmentConfiguration();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
