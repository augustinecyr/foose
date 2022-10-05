package com.sg.vttpminiproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sg.vttpminiproject.models.Contact;
import com.sg.vttpminiproject.repositories.ContactRepository;

@Controller
public class ContactController {
    
    @Autowired
    private ContactRepository conRepo;

    @PostMapping("/success")
	public String contact(@ModelAttribute Contact entry  ) {

        // to test if <entry> gets into the Model & can be seen in terminal
        System.out.println(entry.getName());
        System.out.println("-------------------------");
        System.out.println(entry.getEmail());
        System.out.println("-------------------------");
        System.out.println(entry.getMessage());

        conRepo.save(entry);

		return "success";
	}

    

}
