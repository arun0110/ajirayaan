package com.ajira.ajirayaan.controller;

import com.ajira.ajirayaan.entity.Environment;
import com.ajira.ajirayaan.entity.EnvironmentUpdate;
import com.ajira.ajirayaan.model.EnvironmentConfig;
import com.ajira.ajirayaan.model.Response;
import com.ajira.ajirayaan.service.EnvironmentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/environment")
public class EnvironmentController {


    @Autowired
    EnvironmentConfigService environmentConfigService;

    @GetMapping("ping")
    public String ping(){
        return "its Up!";
    }

    @PostMapping(value = "configure", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> environmentConfiguration(@RequestBody EnvironmentConfig environmentConfig){
        String response = environmentConfigService.saveEnvironmentConfig(environmentConfig);
        if(response.equals("ERROR")){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateEnvironment(@RequestBody EnvironmentUpdate update){
        String resMsg = environmentConfigService.updateEnvironment(update);
        Response response = new Response();
        response.setMessage(resMsg);
        if(resMsg.equals("ERROR")){
            return new ResponseEntity<Response>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
