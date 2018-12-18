package com.ishita.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ishita.pojos.Places;
import com.ishita.pojos.TravellerEntry;

public class TravellerValidator implements Validator {

	
	public boolean supports(Class aClass) {
		return aClass.equals(TravellerEntry.class);
	}

	public void validate(Object obj, Errors errors) {
		TravellerEntry travel = (TravellerEntry) obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.invalid.travel", "City is  Required");
//	/*	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rent", "error.invalid.place", "rent is Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date1", "error.invalid.place", "Start date is Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date2", "error.invalid.place", "end date is Required");
//		*/
//		// check if user exists
//		if(!travel.getCity().matches("^[A-Za-z ]+$")){
//			errors.rejectValue("city", "city_error", "Please enter alphabets only for city");
//			
	//	}
	}
}
