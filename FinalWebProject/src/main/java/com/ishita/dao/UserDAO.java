package com.ishita.dao;

import javax.xml.ws.Response;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.web.servlet.ModelAndView;

import com.ishita.exceptions.UserException;
import com.ishita.pojos.TravellerEntry;
//import com.my.spring.pojo.Email;
import com.ishita.pojos.User;


public class UserDAO extends DAO {

	
	public UserDAO() {
		
		
	}
	
	public User register(User u)
			throws UserException {
		
		try {
			begin();
			System.out.println("inside DAO");

			
			User user = new User();

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setPhonenumber(u.getPhonenumber());
            user.setEmail(u.getEmail());
            user.setUserName(u.getUserName());
            user.setPassword(u.getPassword());
            user.setConfirmPassword(u.getConfirmPassword());
            user.setRole(u.getRole());
            user.setFilename(u.getFilename());
         
            
           
			getSession().save(user);
			commit();
			
//            {
//            	System.out.println("username already exists");
//            }
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	public User get(String userName, String password,String role) throws UserException {
		try {
			begin();
//			System.out.println("inside login");
			Query q = getSession().createQuery("from User where userName = :userName and password = :password and role = :role");
//			System.out.println("try login");
			q.setString("userName", userName);
			q.setString("password", password);	
			q.setString("role",role);
//			System.out.println("test login");
			System.out.println("userName ="+userName+" password = "+password+" role = "+role);
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		} catch (HibernateException e) {
			System.out.println("inside exception"+e.getMessage());
			//rollback();
			throw new UserException("Could not get user " + userName, e);
		}
	}
	
	public User updateUser(TravellerEntry id,User u) throws UserException {
		try {
			begin();
//						
	//		User uu = 
	              //      (User)getSession().get(User.class, userID); 
		getSession().update(u);
			
			commit();
			close();
			return u;
		} catch (HibernateException e) {
			System.out.println("inside exception"+e.getMessage());
			//rollback();
			throw new UserException("Could not get user " + u, e);
		}
	}
	
	public boolean isUserExists(User user){
	begin();
	     boolean result = false;
	     Transaction tx=null;
	     try{
	         tx = getSession().getTransaction();
	         tx.begin();
	         Query query = getSession().createQuery("from User where userName='"+user.getUserName()+"'");
	         User u = (User)query.uniqueResult();
	         tx.commit();
	         if(u!=null) result = true;
	     }catch(Exception ex){
	         if(tx!=null){
	             tx.rollback();
	         }
	     }finally{
	        close();
	     }
	     return result;
	}
	
}
	
	

