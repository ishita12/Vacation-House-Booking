package com.ishita.validators;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ishita.pojos.Places;

public class TravellerHostRequestValidator  implements Validator {

	
	
	
	
	public boolean supports(Class aClass) {
		return aClass.equals(Places.class);
	}

	public void validate(Object obj, Errors errors,HttpServletRequest request) throws ParseException {
		Places travel = (Places) obj;
		
		// check if user exists
		//java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		Date startDate2= travel.getStartDate();
		Date endDate2=travel.getEndDate();
		System.out.println(startDate);
		
		
		
		 SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
         
         java.util.Date sDate = sd.parse(request.getParameter("startDate"));
         java.sql.Date sdateto = new java.sql.Date( sDate.getTime() );
         
         
         java.util.Date dDate = sd.parse(request.getParameter("endDate"));
         java.sql.Date edateto = new java.sql.Date( dDate.getTime() );
         
         
//         Date sDate=sd.parse(startDate);
//         Date eDate=sd.parse(endDate);
         travel.setStartDate(sdateto);
         travel.setEndDate(edateto);
         
		
		if(!(sdateto.compareTo(startDate2)<0)||(sdateto.compareTo(endDate2)>0)){
		errors.rejectValue("startDate", "Date_error", "Dates selected have already been selected");
			
		}
		if(!(edateto.compareTo(startDate2)<0)||(edateto.compareTo(endDate2)>0)){
			errors.rejectValue("endDate", "Date1_error", "Dates selected have already been selected");
				
			}
	
		
		
		
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
}