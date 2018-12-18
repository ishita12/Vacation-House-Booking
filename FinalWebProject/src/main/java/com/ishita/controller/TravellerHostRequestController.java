package com.ishita.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Expression;
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
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import com.ishita.dao.TravellerEntryDAO;
import com.ishita.dao.TravellerHostRequestDAO;
import com.ishita.pojos.PaymentDetails;
import com.ishita.pojos.Places;
import com.ishita.pojos.TravellerEntry;
import com.ishita.pojos.TravellerHostRequest;
import com.ishita.pojos.User;
import com.ishita.validators.PaymentValidator;
import com.ishita.validators.PlaceValidator;
import com.ishita.validators.TravellerHostRequestValidator;
import com.ishita.validators.TravellerValidator;

@Controller
@RequestMapping("/travellerhost/*")
public class TravellerHostRequestController {

	   
	
	@Autowired
	@Qualifier("thDao")
	TravellerHostRequestDAO thDao;
	
	


	@Autowired
	@Qualifier("trValidator")
	TravellerHostRequestValidator trValidator;

	@InitBinder("trValidator")
	private void initBinder1(WebDataBinder binder) {
		binder.setValidator(trValidator);
	}
	
	@Autowired
	@Qualifier("paymentValidator")
	PaymentValidator paymentValidator;

	@InitBinder("paymentValidator")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(paymentValidator);
	}

	@Autowired
	ServletContext servletContext;
	
	       
	

	
	@RequestMapping(value = "/travellerhost/updates", method = RequestMethod.GET)
	
	protected ModelAndView checkRequestUpdates(HttpServletRequest request)throws Exception{
		HttpSession session = (HttpSession) request.getSession();
		User u;
		 u =(User)session.getAttribute("user");
		 long tid=u.getUserID();
		List<TravellerHostRequest>tReq=thDao.getStatus(tid);
		
		
		if(tReq.isEmpty()){
			
			return new ModelAndView("traveller_dashboard");
		}
		
		
		ModelAndView mv=new ModelAndView("requestUpdates");
	mv.addObject("tRequestD", new PaymentDetails());
//		
		mv.addObject("tReq",tReq);
		session.setAttribute("tr",tReq );
		//mv.setViewName("requestUpdates");
		//return new ModelAndView("requestUpdates","tReq",tReq);
	return mv;
		
	}
	
	
	
	
@RequestMapping(value = "/travellerhost/payRequest", method = RequestMethod.POST)
	
	protected ModelAndView makePayment(HttpServletRequest request,@ModelAttribute("payment") PaymentDetails pay, BindingResult result)throws Exception{
		
	paymentValidator.validate(pay, result,request);
	if(result.hasErrors()){
		return new ModelAndView("payment","pay",pay);
		
	}
	
	
	
	HttpSession session = (HttpSession) request.getSession();
		User u;
		 u =(User)session.getAttribute("user");
		 long tid=u.getUserID();
			
		
	//	 PaymentDetails pp=new PaymentDetails();
		String cardNumber=request.getParameter("cardNumber");
		String cvv=request.getParameter("cvv");
		//long requestID=Long.parseLong(request.getParameter("requestID"));
		
//		String travelID=request.getParameter("travelID");
//		String requestID=request.getParameter("requestID");
		pay.setCardNumber(cardNumber);
		pay.setCvv(cvv);

		
		//pay.setTravellerID(travelID);
		 SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
         
         java.util.Date eDate = sd.parse(request.getParameter("expiryDate"));
         java.sql.Date edateto = new java.sql.Date( eDate.getTime() );
         
         
    
        
         
//         java.util.Date pDate = sd.parse(request.getParameter("paymentDate"));
//         java.sql.Date pdateto = new java.sql.Date( pDate.getTime() );
//		
		pay.setExpiryDate(edateto);
		//pay.setPaymentDate(pdateto);
		
		
		   
		List<TravellerHostRequest> ttr=(List<TravellerHostRequest>) session.getAttribute("tr");
		long ttrid=0;
		//long ttrid=ttr.getRequestID();
		
		for(TravellerHostRequest tp:ttr){
			 ttrid=tp.getRequestID();
			
		}
		System.out.println("testing if request id is there or not*******"+ttrid);
		
		List<TravellerHostRequest> tList=thDao.getTravellerList(ttrid);
		TravellerHostRequest trp=null;
		long trequestid=0;
		for(TravellerHostRequest tr:tList){
			trp=tr;
			
			trequestid=tr.getRequestID();
		}
		
		trp.setPayDeatils(pay);
		pay.setHostTraveller(trp);
		
		
		 PaymentDetails pd=thDao.registerPayment(pay);
		 
		 
		 List<TravellerHostRequest>updatedRequestList=null;
		 
		List<PaymentDetails> pdList=thDao.getPaidRequest(pd.getPaymentID());
		 long pdID=0;
		 for(PaymentDetails p:pdList){
			 pdID=p.getPaymentID();
		 }
		 
		 String status="Approve";
		 if(!pdList.isEmpty()){
			 
			int x=thDao.getUpdatedList(ttrid,status);
			System.out.println("values of x is **********"+x);
			 updatedRequestList=thDao.getTraList(tid);
				
		 }
		 
		 
		 ModelAndView mv=new ModelAndView();
		// mv.addObject(attributeName, attributeValue)
		 
//		 mv.addObject("tRequestD",new PaymentDetails());
//		mv.addObject("tReq",updatedRequestList);
//	mv.setViewName("requestUpdates");		
			return new ModelAndView("traveller_dashboard");
		//	return mv;
		
		 }
		 
		 
			 
@RequestMapping(value = "/travellerhost/goback", method = RequestMethod.GET)
protected ModelAndView norequest(HttpServletRequest request) throws Exception {

	
	
	return new ModelAndView("traveller_dashboard");
	
}
	
	
	
	
	
	
@RequestMapping(value = "/travellerhost/pay", method = RequestMethod.POST)
	
	protected ModelAndView payRequest(HttpServletRequest request,  @ModelAttribute("tRequestD")  TravellerHostRequest tRequest)throws Exception{
		HttpSession session = (HttpSession) request.getSession();
		User u;
		 u =(User)session.getAttribute("user");
		 long tid=u.getUserID();
//		List<TravellerHostRequest>tReq=thDao.getStatus(tid);
//		String city=request.getParameter("city");
//		
		
     // TravellerHostRequest tr=null;
     
	//	 long requestID=tRequest.getRequestID();
			
		 
		 String buttonvalue=request.getParameter("r"+tRequest.getRequestID());
			String buttonvalue1=request.getParameter("p"+tRequest.getRequestID());
		long ttrid=0;
		 if(buttonvalue!=null){
		 
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("payment",new PaymentDetails());
		//mv.addObject("trequest",tReq);

		mv.setViewName("payment");		
		//return new ModelAndView("payment","tRequest",tReq);
		return mv;
		 }
		 if(buttonvalue1!=null){
			   
				List<TravellerHostRequest> ttr=(List<TravellerHostRequest>) session.getAttribute("tr");
			
			 for(TravellerHostRequest tp:ttr){
				 ttrid=tp.getRequestID();
			 }
			 
			 String status="Cancelled";
			 int x=thDao.getCancelList(ttrid,status);
			 return new ModelAndView("traveller_dashboard");
			 
		 }
//		 if(buttonvalue1!=null){
//			 //re
//		 }
		 return null;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/travellerhost/request", method = RequestMethod.POST)
	protected ModelAndView makeEntry(HttpServletRequest request,  @ModelAttribute("place")  Places travel, BindingResult result) throws Exception {
		
		
		
		
		
		
		
		List<Places>plist=null;
		List<TravellerHostRequest> travelList=null;
		
		HttpSession session = (HttpSession) request.getSession();
		User u;
		 u =(User)session.getAttribute("user");
		 
		String travellerEmail=request.getParameter("travellerEmail");
		String startDate=request.getParameter("startDate");
		Date startDate2= travel.getStartDate();
		Date endDate2=travel.getEndDate();
		System.out.println(startDate);
		String endDate=request.getParameter("endDate");
		System.out.println("traveller test *******ishita");
		
		System.out.println("traveller email is ************"+travellerEmail);
		 System.out.println("traveller id is***********"+u.getUserID());
		String buttonvalue=request.getParameter("r"+travel.getPlaceID());
		String buttonvalue1=request.getParameter("c"+travel.getPlaceID());
		
		//HashMap<long,String> test=new HashMap<long,String>();

		//TravellerHostRequest  t= null;
		
		if(buttonvalue!=null){
			long h=u.getUserID();
			travel.setHostId(h);
			travel.getPlaceID();
			travel.getPlaceName();
			travel.getRent();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Date s=	df.parse(startDate);
//			Date e=df.parse(endDate);
			
			 java.util.Date sDate = df.parse(request.getParameter("startDate"));
             java.sql.Date s = new java.sql.Date( sDate.getTime() );
             
             java.util.Date dDate = df.parse(request.getParameter("endDate"));
             java.sql.Date e = new java.sql.Date( sDate.getTime() );
             
			System.out.println(s);
			System.out.println(startDate2);
			travelList=thDao.getTravellerRequest(h);
			
		if(travelList.isEmpty()){
			System.out.println("traveller jisne request ki  ka id hai *****");
			TravellerHostRequest th=new TravellerHostRequest();
			th.setTravelId(h);
			th.setDateStarted(s);
			th.setDateEnded(e);
			th.setPlaceID(travel.getPlaceID());
			th.setStatus("pending");
			th.setCity(travel.getPlaceName());
			th.setTravellerName(u.getFirstName());
			th.setTravellerEmail(travellerEmail);
			thDao.register(th);
			
			
			session.setAttribute("status",th.getStatus());
			session.setAttribute("placeID",travel.getPlaceID());
			
		//	System.out.println("traveller ka name and id hai ----"+h+u.getFirstName());
			

			
	plist=thDao.getList(travel.getPlaceName());
	request.setAttribute("traveller",u);
			}
			//if(criteria.add(Expression.gt(s,travelList.get(3))))
//	
//			if(travelList.get(6).getTravelId()!=h){
//
//				TravellerHostRequest th=new TravellerHostRequest();
//				th.setTravelId(h);
//				th.setDateStarted(s);
//				th.setDateEnded(e);
//				th.setPlaceID(travel.getPlaceID());
//				th.setStatus("pending");
//				th.setCity(travel.getPlaceName());
//				th.setTravellerName(u.getFirstName());
//				th.setTravellerEmail(travellerEmail);
//				thDao.register(th);
//				
//				
//				session.setAttribute("status",th.getStatus());
//				session.setAttribute("placeID",travel.getPlaceID());
//				
//			//	System.out.println("traveller ka name and id hai ----"+h+u.getFirstName());
//				
//
//				
//		plist=thDao.getList(travel.getPlaceName());
//		request.setAttribute("traveller",u);
//		
//		
//			}
			
				System.out.println("value of s is******"+s+"value of startDate2 is ******"+startDate2);
		//	for(TravellerHostRequest i:travelList){
		//	System.out.println("traveller jisne request ki***********"+i.getDateStarted().toString());
			//System.out.println("just for testing ***********"+i.getDateStarted()+"**********"+s);
				
				System.out.println("s is ***"+s+"startDate2 is ***"+startDate2+"e is *******"+e+"endDate2 is *******"+endDate2);
				
				
			//if(s.compareTo(startDate2)<0 && e.compareTo(startDate2)<0)
				
			
			//{
			
			System.out.println("dates are equal");
			TravellerHostRequest th=new TravellerHostRequest();
			th.setTravelId(h);
			th.setDateStarted(s);
			th.setDateEnded(e);
			th.setPlaceID(travel.getPlaceID());
			th.setStatus("pending");
			th.setCity(travel.getPlaceName());
			th.setTravellerName(u.getFirstName());
			th.setTravellerEmail(travellerEmail);
			thDao.register(th);
			
			
			session.setAttribute("status",th.getStatus());
			session.setAttribute("placeID",travel.getPlaceID());
			
		//	System.out.println("traveller ka name and id hai ----"+h+u.getFirstName());
			

			
	plist=thDao.getList(travel.getPlaceName());
	request.setAttribute("traveller",u);
	
	
	//		}
			
		//	else{
			//	return new ModelAndView("error");
			//}
		//	}// end of for loop
			
			}
	
	
		if(buttonvalue1!=null){
			
			
			
			long h1=u.getUserID();
			travel.getPlaceID();
			travel.getPlaceName();
			travel.getRent();
		Date sd=	travel.getStartDate();
			Date ed=travel.getEndDate();
			
			
			//String t1=h+sd;
			long rid=0;
			System.out.println("user a id******"+h1);
		//	System.out.println("combination hai **********"+t1);
			List<TravellerHostRequest>thlist=null;
			thlist=thDao.gettList(travel.getPlaceID(), h1);
//			TravellerHostRequest tr=new TravellerHostRequest();
			//long trId=thlist.get(index);
//			String trdate=((TravellerHostRequest) thlist).getDateStarted();
//			
			for(TravellerHostRequest tt:thlist){
				
//				TravellerHostRequest id=thlist.get(5);
//			
//				TravellerHostRequest date=thlist.get(2);
				
				long id=tt.getPlaceID();
				java.util.Date date= tt.getDateStarted();
				
//				
				System.out.println("list ka id and date hai******"+id +"*********"+date);
				if((travel.getPlaceID()==id)&&(sd.compareTo(date)==0)){
					
					
					 rid=tt.getRequestID();
					
				}
				
			}
			
			
			
			
			
//			TravellerHostRequest th=new TravellerHostRequest();
//	
//			th.setRequestID(rid);
//			th.setTravelId(h);
//			th.setDateStarted(sd);
//			th.setDateEnded(ed);
//			th.setPlaceID(travel.getPlaceID());
//			th.setStatus("canceled");
//			
			
			
			for(TravellerHostRequest r:thDao.gettList(travel.getPlaceID(), h1)){
			if(rid==r.getRequestID()){
				r.setStatus("canceled");
				TravellerHostRequest tr=thDao.updateRecord(r);
			}
			}
			
		
			
			

		
plist=thDao.getList(travel.getPlaceName());	
			
			
			
		}
	     
	
		
	
		
	
	
//	@RequestMapping(value = "/travellerhost/delete", method = RequestMethod.POST)
//	protected ModelAndView deleteEntry(HttpServletRequest request,  @ModelAttribute("place")  Places travel, BindingResult result) throws Exception {
//		
//
//		List<Places>plist=null;
//		HttpSession session = (HttpSession) request.getSession();
//		User u;
//		 u =(User)session.getAttribute("user");
//		 
//		
//
//		return new ModelAndView("travellerSearch","travel",plist);
//		
//	}
//	

	
	
	
		
	
	
	
		return new ModelAndView("travellerSearch","travel",plist);}}




