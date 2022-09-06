package com.sg.vttpminiproject.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.sg.vttpminiproject.models.ScoreBat;
import com.sg.vttpminiproject.services.ScoreBatService;


@Controller
@RequestMapping(path = "/highlights")
public class ScoreBatController {
    
    @Autowired
    private ScoreBatService scoreSvc;

    @GetMapping
    public String getHighlights(Model model, HttpSession sess){

        List<ScoreBat> highlights = scoreSvc.getHighlights();
        sess.setAttribute("highlights", highlights);
        model.addAttribute("highlights", highlights);
        
        return "highlights";
    }

}
