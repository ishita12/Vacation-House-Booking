package com.ishita.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ishita.pojos.Places;



public class PlaceValidator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(Places.class);
	}

	public void validate(Object obj, Errors errors,HttpServletRequest request) throws ParseException {
		Places p = (Places) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placeName", "error.invalid.p", "City is  Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rent", "error.invalid.p", "rent is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "error.invalid.p", "Start date is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "error.invalid.p", "end date is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file_name", "error.invalid.p", "end date is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "error.invalid.p", "photos are Required");
		
		// check if user exists
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		 SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
         
         java.util.Date sDate = sd.parse(request.getParameter("startDate"));
         java.sql.Date sdateto = new java.sql.Date( sDate.getTime() );
         
         
         java.util.Date dDate = sd.parse(request.getParameter("endDate"));
         java.sql.Date edateto = new java.sql.Date( dDate.getTime() );
         
         
//         Date sDate=sd.parse(startDate);
//         Date eDate=sd.parse(endDate);
         p.setStartDate(sdateto);
         p.setEndDate(edateto);
         
		
		if(edateto.compareTo(sdateto)<0){
		errors.rejectValue("endDate", "Date_error", "End date should be greater than the start date");
			
		}
		
		if(!p.getPlaceName().matches("^[A-Za-z ]+$")){
			errors.rejectValue("placeName", "place_error", "Please enter alphabets only for place name");
				
		}
		
		
		
		if(sdateto.compareTo(sqlDate)<0){
			errors.rejectValue("startDate", "Date1_error", "Start date should be after today's date");
			
		}
		
		
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
}
