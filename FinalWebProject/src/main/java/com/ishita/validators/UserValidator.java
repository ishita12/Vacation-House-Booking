package com.ishita.validators;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ishita.pojos.User;



public class UserValidator implements Validator{

	
	
		public boolean supports(Class aClass) {
			return aClass.equals(User.class);
		}

		public void validate(Object obj, Errors errors) {
			User user = (User) obj;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "error.invalid.user", "Phone Number Required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.user", "User Name Required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "error.invalid.user", "Phone Number Required");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.invalid.password", " Confirm Password Required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.emailaddress",
					"Email Required");	
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "error.invalid.user", "Select your role");
			
			if(!user.getFirstName().matches("^[A-Za-z ]+$")){
				errors.rejectValue("firstName", "firstName_error", "Please enter alphabets only for First name");
				
			}
			
			
			if(!user.getLastName().matches("^[A-Za-z ]+$")){
				errors.rejectValue("lastName", "lastName_error", "Please enter alphabets only for last name");
				
			}
			
			
//			if(!user.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+[A-Z]{2,}+$")){
//				errors.rejectValue("email", "email_error", "Please enter a valid email address");
//					
//			}
			
			
		if(!user.getPhonenumber().matches("[0-9]{3}[-]{1}[0-9]{4}[-]{1}[0-9]{3}$")){				
				errors.rejectValue("phonenumber", "phone_error", "Please enter a valid phone number of the pattern 111-1111-111");
				
			}
			
			
			
			if(!user.getPassword().equals(user.getConfirmPassword())){
				errors.rejectValue("confirmPassword", "confirmPassword_error", "Password and Confirm password don't match");
				
			}
			
			
		}
}
