package com.sg.vttpminiproject.controllers;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/highlights" , produces=MediaType.APPLICATION_JSON_VALUE)
public class ScoreBatController {
    
    @GetMapping
    public String getHighlights(Model Model){

        
        return "highlights";
    }

}
