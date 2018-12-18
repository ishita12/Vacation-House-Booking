package com.ishita.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ishita.pojos.PaymentDetails;
import com.ishita.pojos.Places;

public class PaymentValidator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(PaymentDetails.class);
	}

	public void validate(Object obj, Errors errors,HttpServletRequest request) throws ParseException {
		PaymentDetails pay = (PaymentDetails) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "error.invalid.pay", "Card number is  Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cvv", "error.invalid.pay", "cvv is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "error.invalid.pay", "Expiry date is Required");
		
		
		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		 SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date eDate = sd.parse(request.getParameter("expiryDate"));
         java.sql.Date edateto = new java.sql.Date( eDate.getTime() );
        
		
		if(edateto.compareTo(sqlDate)<0){
			errors.rejectValue("expiryDate", "Date11_error", "Enter a valid expiry date");
			
		}
		
		if(!pay.getCardNumber().matches("[0-9]{4}[-]{1}[0-9]{4}[-]{1}[0-9]{4}$")){				
			errors.rejectValue("cardNumber", "card_error", "Please enter a valid card number of the pattern 1111-1111-1111");
			
		}
		
		if(!pay.getCvv().matches("[0-9]{3}$")){				
			errors.rejectValue("cvv", "cvv_error", "Please enter a valid CVV of the pattern 111");
			
		}
		
		

		
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
}
