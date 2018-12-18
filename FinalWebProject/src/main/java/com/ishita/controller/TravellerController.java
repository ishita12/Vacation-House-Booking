package com.ishita.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ishita.dao.PlaceDAO;
import com.ishita.dao.TravellerEntryDAO;
import com.ishita.dao.UserDAO;
import com.ishita.pojos.Images;
import com.ishita.pojos.Places;
import com.ishita.pojos.TravellerEntry;
import com.ishita.pojos.User;
import com.ishita.validators.PlaceValidator;
import com.ishita.validators.TravellerValidator;


@Controller
@RequestMapping("/traveller/*")
public class TravellerController {

	
	@Autowired
	@Qualifier("tentryDao")
	TravellerEntryDAO tentryDao;
	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("tentryValidator")
	TravellerValidator tentryValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(tentryValidator);
	}

	@Autowired
	ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/traveller/entry", method = RequestMethod.GET)
	protected ModelAndView makeEntry(ModelMap model) throws Exception {

		
		
		return new ModelAndView("traveller_form","travel",new TravellerEntry());
		
	}
	
	
	@RequestMapping(value = "/traveller/search", method = RequestMethod.POST)
	protected ModelAndView searchPlace(HttpServletRequest request) throws Exception {
//		tentryValidator.validate(travel, result);
//		if(result.hasErrors()){
//			return new ModelAndView("traveller_dashboard","travel",new TravellerEntry());
//			
//		}
		String key=request.getParameter("searchplace");
		HttpSession session = (HttpSession) request.getSession();
		User u;
		
		 List<Places> placeList = null;
			try {
				placeList = tentryDao.get(key);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//List<Images>imageList=null;
			//imageList=
		
			 u =(User)session.getAttribute("user");
			 
			 String te=u.getEmail();
			 
			 request.setAttribute("temail",te );
			 
			 System.out.println("traveller email in traveller controller is *********"+te);
				System.out.println("user ka name hai :**********"+u.getFirstName());

		return new ModelAndView("travellerSearch","travel",placeList);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value = "/traveller/entry", method = RequestMethod.POST)
	protected ModelAndView makeEntry(HttpServletRequest request,  @ModelAttribute("travel")  TravellerEntry travel, BindingResult result) throws Exception {
		TravellerEntry tt=new TravellerEntry();
		//tentryValidator.validate(travel, result);
		HttpSession session = (HttpSession) request.getSession();
		User u;
		System.out.println("result error"+result);
		System.out.println("testing traveller");
		if (result.hasErrors()) {
			
			return new ModelAndView("traveller_form", "travel",travel);
		}

		try {

			 u =(User)session.getAttribute("user");
			travel.setTravellerId(u.getUserID());

		tentryDao.register(travel);	


	} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//			return new ModelAndView("error", "errorMessage", "error while login");
	}
		
		return new ModelAndView("traveller_profile","travel",tt);
	}
	
	
}
