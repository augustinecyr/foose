package com.sg.vttpminiproject.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sg.vttpminiproject.models.Contact;
import com.sg.vttpminiproject.repositories.ContactRepository;

@RestController
public class ContactRestController {

    @Autowired
    private ContactRepository conRepo;

    @GetMapping(path = "/contactrest/{email}", produces = "application/json")
    public ResponseEntity<String> getMsg(@PathVariable String email, @ModelAttribute Optional<Contact> contact) {

        contact = conRepo.get(email);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(contact.toString());

    }

}
