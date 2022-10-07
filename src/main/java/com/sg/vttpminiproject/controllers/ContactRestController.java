package com.sg.vttpminiproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sg.vttpminiproject.repositories.ContactRepository;

@RestController
public class ContactRestController {

    @Autowired
    private ContactRepository conRepo;

    @Qualifier("redislab")

    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(path = "/contactrest/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMsg(@PathVariable String email) {

        // get existing email from ContactRepository
        String opt = conRepo.get(email);

        return ResponseEntity.ok(opt);

    }

}
