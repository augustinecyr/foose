package com.sg.vttpminiproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sg.vttpminiproject.models.TransferMarkt;
import com.sg.vttpminiproject.services.TransferMarktService;

@Controller
@RequestMapping(path = "/leaguetable")
public class TransferMarktController {

    @Autowired
    private TransferMarktService trfMktSvc;

    @GetMapping
    public String getTable(Model model, HttpSession sess, @RequestParam String id, @RequestParam String seasonID) {

        trfMktSvc.getTable(id, seasonID);
        List<TransferMarkt> tables = trfMktSvc.getTable(id, seasonID);
        sess.setAttribute("tables", tables);
        model.addAttribute("tables", tables);
        model.addAttribute("id", id);
        model.addAttribute("seasonID", seasonID);
        return "leaguetable";

    }

}
