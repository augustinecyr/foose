package com.sg.vttpminiproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sg.vttpminiproject.models.Contact;

@Controller
public class ContactController {
    
    @RequestMapping(value="/simpleform" , method = RequestMethod.GET)
    public void simpleForm(Model model){
        model.addAttribute(new Contact());
    }

    @RequestMapping(value="/formoutput" , method = RequestMethod.POST)
    public void simple(@ModelAttribute Contact contact , Model model){
        model.addAttribute("contact", contact);
    }
}
