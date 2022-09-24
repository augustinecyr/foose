package com.sg.vttpminiproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
	public String home() {
		
		return "index";
	}

    @GetMapping("/about")
	public String aboutUs() {
		
		return "about";
	}

    @GetMapping("/searchtable")
	public String searchTable() {
		
		return "searchtable";
	}

    @GetMapping("/contactus")
	public String contactUs() {
		
		return "contact";
	}

    @GetMapping("/register")
    public String register(){
        return "register";
    }


}
