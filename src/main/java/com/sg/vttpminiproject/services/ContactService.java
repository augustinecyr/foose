package com.sg.vttpminiproject.services;

import org.springframework.stereotype.Service;

import com.sg.vttpminiproject.models.Contact;

import java.util.*;


@Service
public class ContactService {
    private List<Contact> entry = new ArrayList<Contact>();

    public List<Contact> getEntries() {
        return this.entry;
    }

    public void addEntry(Contact e) {
        entry.add(new Contact(e.getEmail(), e.getName(), e.getMessage()));
    }

   
}
