package com.ajira.ajirayaan.controller;

import com.ajira.ajirayaan.model.*;
import com.ajira.ajirayaan.service.RoverConfigService;
import com.ajira.ajirayaan.service.StatusResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rover")
public class RoverController {

    @Autowired
    RoverConfigService roverConfigService;
    @Autowired
    StatusResponseService statusResponseService;

    @PostMapping("configure")
    public ResponseEntity<String> initialRoverConfiguration(@RequestBody RoverConfig roverConfig){
        String response = roverConfigService.saveRoverConfigurations(roverConfig);
        if(response.equals("ERROR")){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("status")
    public ResponseEntity<Status> getStatus(){
        Status status = statusResponseService.getStatus();
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @PostMapping("move")
    public ResponseEntity<Response> makeMove(@RequestBody Move move){
        Response response = new Response();
        String responseMsg = roverConfigService.makeMove(move.getDirection());
        response.setMessage(responseMsg);
        if(responseMsg.equals("SUCCESS")){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        if(responseMsg.equals("ERROR")){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(response,HttpStatus.PRECONDITION_REQUIRED);
    }
}
