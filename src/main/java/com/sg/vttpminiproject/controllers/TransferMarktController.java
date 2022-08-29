package com.sg.vttpminiproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.vttpminiproject.models.TransferMarkt;
import com.sg.vttpminiproject.services.TransferMarktService;

@RestController
@RequestMapping(path = "/leaguetable")
public class TransferMarktController {
    @Autowired
    private TransferMarktService trfMktSvc;

    @GetMapping
    public String getTable(Model model, HttpSession sess, @RequestParam String id, @RequestParam String seasonID) {

        trfMktSvc.getTable(id,seasonID);
        List<TransferMarkt> table = trfMktSvc.getTable(id, seasonID);
        sess.setAttribute("table", table);
        model.addAttribute("table", table);
        model.addAttribute("id", id);
        model.addAttribute("seasonID", seasonID);

        return "twitter";

    }
}
