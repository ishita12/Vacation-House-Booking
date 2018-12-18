package com.ishita.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ishita.dao.UserDAO;
import com.ishita.pojos.Places;
import com.ishita.pojos.TravellerEntry;
import com.ishita.pojos.User;
import com.ishita.validators.UserValidator;
import com.ishita.exceptions.UserException;


@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder("userValidator")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	@Autowired
	ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	
	
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("signup", "user", new User());

	}

	
	@RequestMapping(value ="/user/logout", method = RequestMethod.POST)
	protected ModelAndView logout() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("signup", "user", new User());

	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request,  @ModelAttribute("user") @Valid User user, BindingResult result) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		User u = userDao.get(request.getParameter("userName"), request.getParameter("password"),request.getParameter("role"));
	
		try {

			System.out.print("loginUser");
			
			
	
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error", "user", user);
			}
			
			session.setAttribute("user", u);
		
			//return u.getRole()+"_dashboard";
			
		}
		catch(Exception e){
			
		}
		/*catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("error", "user", user);
		}*/
		if(u.getRole().equals("host")){
			
			return new ModelAndView("host_dashboard", "user", u);
		}
		else
		{
			return new ModelAndView("traveller_dashboard", "user", u);	
			}
		
		//return new ModelAndView("userhome", "user", user);
	}
	
	
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("signup", "user", user);
		}
//		TravellerEntry te=new TravellerEntry();
//		te.set
//		user.getTravellerEntry().add(te);
		Boolean b=true;
		String choice =request.getParameter("role");
		System.out.println(choice);
		System.out.println("inside signup");
		User u=new User();
		

		try {
			System.out.println("inside file upload");
			
				System.out.println("inside img");
				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				String path = null;
				/*if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}*/
				if (check.equalsIgnoreCase("\\")) {
					path = "D:"; // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					//path = servletContext.getRealPath("").replace("build/", "");
					path = "D:"; 
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "\\" +"myprofilephoto");
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = user.getPhoto();

					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					user.setFilename(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					
					if(userDao.isUserExists(user)){
				return new ModelAndView("errorsignup","user",user);		
						
					}
					 u = userDao.register(user);
					 request.getSession().setAttribute("user", u);
				} else {
					System.out.println("Failed to create directory!");
				}
			

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} 
		catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
		if(choice.equals("host")){
			return new ModelAndView("host_dashboard", "user", u);
		}
		else if(choice.equals("traveller")){
			System.out.println("inside traveller");
			return new ModelAndView("traveller_dashboard", "user", u);
		}
		return new ModelAndView("userhome", "user", user);
	}}
