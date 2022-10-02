package com.sg.vttpminiproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.vttpminiproject.models.Contact;
import com.sg.vttpminiproject.models.ContactForm;
import com.sg.vttpminiproject.services.ContactService;


@Controller
@RequestMapping(path = "/")
public class ContactController {
    private List<Contact> entry = new ArrayList<Contact>();

    @Autowired
   private ContactService conSvc;

    @GetMapping(value = "/testRetrieve", produces = "application/json")
    public @ResponseBody List<Contact> getAllEntries() {
        entry = conSvc.getEntries();

        return entry;
    }


    @GetMapping(value = "/submitform")
    public String entry(Model model) {
        entry = conSvc.getEntries();
        model.addAttribute("persons", entry);

        return "submitform";
    }


    @GetMapping(value = "/addEntry")
    public String showAddEntryPage(Model model) {
        ContactForm cForm = new ContactForm();
        model.addAttribute("contactForm", cForm);

        return "addEntry";
    }

 
    @PostMapping(value = "/addEntry")
    public String savePerson(Model model,
            @ModelAttribute("contactForm") ContactForm contactForm) {

        String email = contactForm.getEmail();
        String name = contactForm.getName();
        

        if (email != null && name.length() > 0 ) {
            Contact newEntry = new Contact(email, name);
            conSvc.addEntry(newEntry);

            return "redirect:/submitform";
        }

       
        return "addEntry";
    }


}
