package com.sg.vttpminiproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.sg.vttpminiproject.models.Twitter;
import com.sg.vttpminiproject.services.TwitterService;

@Controller
@RequestMapping ("/twitter")
public class TwitterController {

    @Autowired
    private TwitterService twitterSvc;

    @GetMapping
    public String getTweets(Model model , HttpSession sess ) {

        // id = 330262748;
        List<Twitter> tweets = twitterSvc.getTweets(); 
        sess.setAttribute("tweets", tweets);
        model.addAttribute("tweets", tweets);
        
        return "twitter";

    }

}
