package com.ishita.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ishita.dao.PlaceDAO;
import com.ishita.exceptions.UserException;
import com.ishita.pojos.Images;
import com.ishita.pojos.Places;
import com.ishita.pojos.Reply;
import com.ishita.pojos.TravellerEntry;
import com.ishita.pojos.TravellerHostRequest;
import com.ishita.pojos.User;
import com.ishita.validators.PlaceValidator;

@Controller
@RequestMapping("/host/*")
public class PlaceController {

	
	@Autowired
	@Qualifier("placeDao")
	PlaceDAO placeDao;

	@Autowired
	@Qualifier("placeValidator")
	PlaceValidator placevalidator;

	@InitBinder("placeValidator")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(placevalidator);
	}

	@Autowired
	ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/host/enter", method = RequestMethod.GET)
	protected ModelAndView makeEntry(HttpServletRequest request) throws Exception {

		
		
		return new ModelAndView("image_form","place",new Places());
		
		
	}
	
	
	@RequestMapping(value = "/host/goback", method = RequestMethod.GET)
	protected ModelAndView norequest(HttpServletRequest request) throws Exception {

		
		
		return new ModelAndView("host_dashboard");
		
	}
	
	
	
//	@RequestMapping(value = "/host/enter", method = RequestMethod.POST)
//	protected ModelAndView entry(HttpServletRequest request) throws Exception {
//HttpSession session=request.getSession(true);
//
//		int no=Integer.parseInt(request.getParameter("numberOfPlaces"));
//		session.setAttribute("num",no);
//		
//		return new ModelAndView("image_form","place",new Places());
//		
//	}
	

	@RequestMapping(value = "/host/upload", method = RequestMethod.POST)
	
protected ModelAndView ImageUpload(HttpServletRequest request,  @ModelAttribute("place") Places p, BindingResult result) throws Exception {
		placevalidator.validate(p, result, request);
		if(result.hasErrors()){
			return new ModelAndView("image_form","p",new Places());
			
		}
		HttpSession session = (HttpSession) request.getSession();
		
		//Places place=new Places();
		User u;
		String choice =request.getParameter("role");
		System.out.println(choice);
		System.out.println("inside signup");
		
		
		
		
		
	
int i=0;

ArrayList<Images> imageList=new ArrayList();
Places pp=new Places();

		try {
			 u =(User)session.getAttribute("user");
			 p.setHostId(u.getUserID());
			if (p.getFile_name().trim() != "" || p.getFile_name() != null) {
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
////																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					//path = servletContext.getRealPath("").replace("build/", "");
					path = "D:"; 
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "\\" +p.getFile_name());
				boolean temp = directory.exists();
			if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile[] photoInMemory = p.getPhoto();

				
					// move the file from memory to the file
                    for(int j=0;j<photoInMemory.length;j++){
                    	Images images=new Images();
                    	String fileName = photoInMemory[j].getOriginalFilename();
    					// could generate file names as well

    					File localFile = new File(directory.getPath(), fileName);
					photoInMemory[j].transferTo(localFile);
					
					//set the object of iamge in list
					images.setFileName(localFile.getPath());
					p.setFile_name(localFile.getPath());
				p.getImages().add(images);
					System.out.println("File is stored at" + localFile.getPath());
					System.out.print("registerNewUser");
					images.setPlaces(p);
                    }
                    
                           SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
                    
                    java.util.Date sDate = sd.parse(request.getParameter("startDate"));
                    java.sql.Date sdateto = new java.sql.Date( sDate.getTime() );
                    
                    
                    java.util.Date dDate = sd.parse(request.getParameter("endDate"));
                    java.sql.Date edateto = new java.sql.Date( dDate.getTime() );
                    
                    
//                    Date sDate=sd.parse(startDate);
//                    Date eDate=sd.parse(endDate);
                    
                      p.setStartDate(sdateto);
                    p.setEndDate(edateto);
                    
                     pp=placeDao.register(p);
                    
                  
//                     n=n-1;
//                   session.setAttribute("num",n);
//                   if(n==0){
//                	   return new ModelAndView("userhome","place",p);
//                   }

				} else {
					System.out.println("Failed to create directory!");
				}
			}

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
	} 
	//	RequestDispatcher rd=request.getRequestDispatcher("traveller_profile.jsp");
		
		
//	
		return new ModelAndView("host_dashboard");
//	//	return new ModelAndView("userhome");
 }
	

	@RequestMapping(value = "/host/processRequest", method = RequestMethod.POST)
	protected ModelAndView requestProcess(HttpServletRequest request,  @ModelAttribute("travelRequest") TravellerHostRequest travelRequest) throws Exception{
		
		System.out.println("lets check**********************");
		HttpSession session = (HttpSession) request.getSession();
		User u;
		u =(User)session.getAttribute("user");
		long requestID=travelRequest.getRequestID();
		
		System.out.println("inside host reply");
//		String tname=travelRequest.getTravellerName();
		
		
		
//		
//		String d1=request.getParameter("dateStarted");
//	String d2=request.getParameter("dateEnded");
	String city=request.getParameter("city");
		String travellerName=request.getParameter("travellerName");
		String status=request.getParameter("status");
		long pid=travelRequest.getPlaceID();
	
session.setAttribute("pid",pid);
//		
//		s
		//call update method to get list of only request that have pending status
		//update status in host request table also
		
		
		//request.setAttribute("travellerName",tname);
		
		Reply reply=new Reply();
		//change status to 
		
		//reply.setMessage(message);
		
//		travelRequest.getCity();
//		travelRequest.getDateEnded();
//		travelRequest.getDateStarted();
//		travelRequest.getPlaceID();
//		travelRequest.getStatus();
//		travelRequest.getRequestID();
//		travelRequest.getTravelId();
//		travelRequest.getTravellerName();
		
		
//		travelRequest.setCity(city);
//		travelRequest.setDateEnded(dd2);
//		travelRequest.setDateStarted(dd1);
//		travelRequest.setStatus(status);
//		travelRequest.setTravellerName(travellerName);
//		List<TravellerHostRequest>tr=null;
//		tr= placeDao.getTravellerRequestList(requestID);
//		//reply.setTrequest(travelRequest);
		session.setAttribute("trid",requestID);
		session.setAttribute("travellerName",travellerName);
		//reply.setTravellerName(tname);
		
		return new ModelAndView("reply","reply",new Reply());
		
	}

	
//	@RequestMapping(value = "/host/reply", method = RequestMethod.POST)
//	protected ModelAndView requestReply(HttpServletRequest request,  @ModelAttribute("reply") Reply reply) throws Exception{
//		
//		
//		HttpSession session = (HttpSession) request.getSession();
//		User u;
//		u =(User)session.getAttribute("user");
//		
//	
//		
//		
//		Reply rr=null;
//		reply.getHostName();
//		reply.getTravellerName();
//		
//		rr=placeDao.registerReply(reply);	
//		
//		return new ModelAndView("test","test",rr);
//		
//	}
	
	
	@RequestMapping(value="/host/reply",method=RequestMethod.POST)
	protected ModelAndView rreply(HttpServletRequest request,@ModelAttribute("reply")Reply reply)throws Exception{
		HttpSession session=(HttpSession) request.getSession();
		User u;
		u=(User) session.getAttribute("user");
		//TravellerHostRequest trequest=null;
		//Reply rr=new Reply();
//		reply.getHostName();
//		reply.getTravellerName();
//		
long placeID=(Long) session.getAttribute("pid");
//		
//		reply.getMessage();
//		reply.getHostName();
//		reply.getReplyID();
//		reply.getStatus();
//		reply.getTravellerName();
		
		long uid=u.getUserID();
		String uemail=u.getEmail();
		String tName=(String) session.getAttribute("travellerName");
		reply.setTravellerName(tName);
		
		System.out.println("values of place id is "+placeID);
		List<TravellerHostRequest> statusList=placeDao.checkStatus(placeID);
			//System.out.println("status is "+status);
//			if(status=="pending"){
		
		
		for(TravellerHostRequest tp:statusList){
			if(tp.getStatus().equals("Approve")||tp.getStatus().equals("In progress")){
				
			return new ModelAndView("ExistingEntry");
		}
			else
			{
		List<Places>pplist=null;
		
		pplist = placeDao.getList(uid);
		List<TravellerHostRequest> requestList=null;
		List<TravellerHostRequest> pendingList=null;
		requestList= placeDao.getRequestList(pplist);
		
		TravellerHostRequest th=null;
		long tr= (Long) session.getAttribute("trid");
		List<User>uList=placeDao.getTid(tr);
		String temail=null;
		
		long tid=0;
		
		//long tr1=Long.parseLong(tr);
		List<TravellerHostRequest> travelList=placeDao.getTravellerRequestList(tr);
		for(TravellerHostRequest t:travelList){
			th=t;
			 tid=t.getTravelId();
		}
		
		
		
		List<User> temailList=placeDao.getEmailTraveller(tid);
		
		for(User u1:temailList){
			temail=u1.getEmail();
		}
		
		th.setReply(reply);
		reply.setTrequest(th);
		
		pendingList=placeDao.getPendingList(requestList);
		
		//TravellerHostRequest tpl=null;
		
		
		
		
		
		if(reply.getStatus().equals("Approve")){
		th.setStatus("In progress");
		
				

		
		 Email email = new SimpleEmail();
		
		 email.setHostName("smtp.googlemail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
         email.setSmtpPort(465);
         email.setAuthenticator(new DefaultAuthenticator("urvalchopra@gmail.com", "Urval@chopra"));
         email.setSSLOnConnect(true);
         email.setFrom(uemail);//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
         email.setSubject("Welcome to couch surfing");
         email.setMsg(reply.getMessage());
         email.addTo(temail);//Will come from the database
         email.send();
		}
		else if(reply.getStatus().equals("Reject")){
			th.setStatus("Request Rejected");
			 Email email = new SimpleEmail();
				
			 email.setHostName("smtp.googlemail.com");//If a server is capable of sending email, then you don't need the authentication. In this case, an email server needs to be running on that machine. Since we are running this application on the localhost and we don't have a email server, we are simply asking gmail to relay this email.
	         email.setSmtpPort(465);
	         email.setAuthenticator(new DefaultAuthenticator("urvalchopra@gmail.com", "Urval@chopra"));
	         email.setSSLOnConnect(true);
	         email.setFrom(uemail);//This email will appear in the from field of the sending email. It doesn't have to be a real email address.This could be used for phishing/spoofing!
	         email.setSubject("Welcome to couch surfing");
	         email.setMsg(reply.getMessage());
	         email.addTo(temail);//Will come from the database
	         email.send();
               
            
		}
		
		if(pendingList.isEmpty()){
			return new ModelAndView("HostNoRequest");
			
		}
		
		
		System.out.println("aa1");
		
		
		
		
			
			//			
			
			placeDao.getSession().update(th);

			Reply rr=placeDao.registerReply(reply);

			
			
			
			
			
			
			
			
			return new ModelAndView("hostRequest","travelRequest",pendingList);

			
		}
	}
	//		}
//		else{
//			return new ModelAndView("errorAcceptRequest");
//					
//			
//		}
		
		//trequest.setReply(reply);
		//trequest=reply.getTrequest();

		
		//System.out.println(trequest.getStatus());
		
		
		
		
		
//		trequest.getCity();
//		trequest.getDateEnded();
//		trequest.getDateStarted();
//		trequest.getPlaceID();
//		trequest.getRequestID();
//		trequest.getStatus();
//		trequest.getTravelId();
//		trequest.getTravellerName();
	//	reply.setTrequest(trequest);
		//trequest.setReply(reply);
//reply.getTrequest().setStatus(reply.getStatus());
		
		

		return new ModelAndView("null");
	}











	@RequestMapping(value = "/host/search", method = RequestMethod.POST)
	protected ModelAndView requestForHosts(HttpServletRequest request) throws Exception {

		List<Places>plist=null;
HttpSession session = (HttpSession) request.getSession();
User u;
u =(User)session.getAttribute("user");
long uid=u.getUserID();
 List<Places> placeList = null;
 //System.out.println("uid***************"+placeDao.getList(uid));
	
		placeList = placeDao.getList(uid);
		
		
		
		//System.out.println("place one"+placeList.get(1));
		
		
		
		
		
	
//		System.out.println("place id one is"+placeList.get(0));
//		System.out.println("place id two is"+placeList.get(1));

	//List<Images>imageList=null;
	//imageList=
		
	//	try{
//	for(int i=0;i<placeList.size();i++){
//		
//		System.out.println("list items*************"+placeList.get(i));
////		System.out.println("list items*************"+placeList.get(2));
////		System.out.println("list items*************"+placeList.get(3));
//		
////		TravellerHostRequest id=thlist.get(5);
////	
////		TravellerHostRequest date=thlist.get(2);
//	
////		long id=p.getPlaceID();
////	
//////		
////		System.out.println("hostki side se request**********"+id);
//			
//		
//		
//	}
	
	
	//	}catch(Exception e)
		//{
			//e.printStackTrace();
	//	}
	
List<TravellerHostRequest> requestList=null;
	List<TravellerHostRequest> pendingList=null;
	requestList= placeDao.getRequestList(placeList);
	
	
	pendingList=placeDao.getPendingList(requestList);
	
	
	
	if(pendingList.isEmpty()){
		return new ModelAndView("HostNoRequest");
		
	}
	
//`	System.out.println("placeid is"+requestList.get(3));
	
	
//	for(int i=0; i<requestList.size();i++){
//	System.out.println("details of first request for host are"+requestList.get(0)+""+requestList.get(1)+""+requestList.get(2)+""+requestList.get(3)+""+requestList.get(4)+""+requestList.get(5));
//	
//	}
		//System.out.println("host ka name hai :**********"+u.getFirstName());

return new ModelAndView("hostRequest","travelRequest",pendingList);

	}



}
//	
