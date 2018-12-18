package com.ishita.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/logout/*")
public class logoutController {

	
	@RequestMapping(value = "/logout/", method = RequestMethod.POST)
    public String logout(HttpServletRequest request){
        
        HttpSession session = request.getSession();
        session.invalidate();
        return "logout";
    }
	
	
}
