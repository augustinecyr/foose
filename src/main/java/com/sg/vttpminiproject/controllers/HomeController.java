package com.sg.vttpminiproject.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
	public String home(Model model,
	@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
	@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("userName", oauth2User.getName());
		return "index";
	}

    @GetMapping("/about")
	public String aboutUs() {
		
		return "about";
	}

    @GetMapping("/searchtable")
	public String searchTable(Model model,
	@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
	@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("userName", oauth2User.getName());
		return "searchtable";
	}

    @GetMapping("/contactus")
	public String contactUs() {
		
		return "contactus";
	}

    @GetMapping("/register")
    public String register(){
        return "register";
    }

	@GetMapping("/loginpage")
    public String login(){
        return "loginpage";
    }
	

}
