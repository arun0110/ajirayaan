package com.ajira.ajirayaan.controller;

import com.ajira.ajirayaan.model.*;
import com.ajira.ajirayaan.model.request.RoverConfig;
import com.ajira.ajirayaan.service.AjirayaanService;
import com.ajira.ajirayaan.service.RoverConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rover Controller is to handle requests related to Rover
 * initial Configurations and getting status also to make
 * Rover move.
 */
@RestController
@RequestMapping("api/rover")
public class RoverController {

    @Autowired
    AjirayaanService ajirayaanService;

    /**
     * Post mapping for Initial Rover Configurations
     * @param roverConfig rover Config JSON values
     * @return response as Http error messages
     */
    @PostMapping("configure")
    public ResponseEntity<String> initialRoverConfiguration(@RequestBody RoverConfig roverConfig){
        String response = ajirayaanService.saveRoverConfigurations(roverConfig);
        if(response.equals("ERROR")){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get Request to get the current Rover and Environment status
     * @return response as Http error messages
     */
    @GetMapping("status")
    public ResponseEntity<Status> getStatus(){
        Status status = ajirayaanService.getStatus();
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    /**
     * Post mapping to move rover step by step based on given direction
     * @param move is a direction like UP | DOWN | LEFT | RIGHT
     * @return response as Http error messages
     */
    @PostMapping("move")
    public ResponseEntity<Response> makeMove(@RequestBody Move move){
        Response response = new Response();
        String responseMsg = ajirayaanService.makeMove(move.getDirection());
        response.setMessage(responseMsg);
        if(responseMsg.equals("SUCCESS")){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        if(responseMsg.equals("ERROR")){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(response,HttpStatus.PRECONDITION_REQUIRED);
    }

    /**
     * Get Mapping to flush Rover configurations for test purpose
     * And also In case if we want to save different Configurations
     * @return response as Http error messages
     */
    @GetMapping("flush")
    public ResponseEntity<String> flush(){
        ajirayaanService.flushRoverConfiguration();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
