package com.ishita.dao;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.web.servlet.ModelAndView;

import com.ishita.exceptions.UserException;
import com.ishita.pojos.Places;
import com.ishita.pojos.Reply;
import com.ishita.pojos.TravellerHostRequest;
import com.ishita.pojos.User;

public class PlaceDAO extends DAO {

	
	
	
	
	
	
	public List<User> getEmailTraveller(long tid)throws ParseException{
		
		
		List<User>u1List=null;
		try {
			begin();
			
			
			begin();
			
			Query query = getSession().createQuery("from User where userID = :k");
			query.setLong("k",tid);	
			
			u1List=query.list();
			
         
		
			commit();
			

		}catch (HibernateException e) {
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return u1List;
		
		
	}
	
	
	
	
	
	
	
	
	
	public List<User> getTid(long tr)throws ParseException{
		List<User>uList=null;
		try {
			begin();
			
			
			begin();
			
			Query query = getSession().createQuery("from User where userID = :k");
			query.setLong("k",tr);	
			
			uList=query.list();
			
         
		
			commit();
			

		}catch (HibernateException e) {
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return uList;
		
	}
	
	
	
	
	public Places register(Places p)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			
			
         
			
//		place.setPlaceName(p.getPlaceName());
//            place.setRent(p.getRent());			
//			place.setFile_name(p.getFile_name());
			getSession().save(p);
			commit();
			return p;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while adding places: " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	public Reply registerReply(Reply r)
			throws UserException {
		try {
			begin();
			System.out.println("inside reply");

			System.out.println("traveller ki reqest i dhai ********"+r.getTrequest().getRequestID());
			
         
			
//		place.setPlaceName(p.getPlaceName());
//            place.setRent(p.getRent());			
//			place.setFile_name(p.getFile_name());
			getSession().save(r);
			commit();
			return r;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while adding places: " + e.getMessage());
		}
	}
	
	
	
	
	
	public List<TravellerHostRequest> checkStatus(long placeID)throws ParseException{
		String status=null;
		List<TravellerHostRequest> trList=null;
		try {
			
			
			begin();
			
			Query query = getSession().createQuery("from TravellerHostRequest where placeId = :k");
			query.setLong("k",placeID);	
			
			trList=query.list();
			
			
			
			
			
			//query.setString("p",placeName);	
			getSession().save(query);
			//System.out.println("place id ***************"+placeList.get(1));
			commit();
			
			
		} catch (HibernateException e) {
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return trList;
		
	}
	
	
	
	

	public List<Places> getList(long hostId)throws ParseException{
		List<Places> placeList=null;
		try {
		begin();
		
		Query query = getSession().createQuery("select placeID from Places where hostId = :k");
		query.setLong("k",hostId);	
		//query.setString("p",placeName);	
		placeList =query.list();
		//System.out.println("place id ***************"+placeList.get(1));
		commit();
		
	} catch (HibernateException e) {
		rollback();
		try {
			throw new UserException("Could not get user with " + e);
		} catch (UserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		return placeList;
		
	}
	
	public List<TravellerHostRequest> getRequestList(List<Places> placeList)throws ParseException{
	
		System.out.println("elements inside dao :");
		List<TravellerHostRequest> requestList=null;
		try{
			begin();
			
			Query query=getSession().createQuery("from TravellerHostRequest where placeID in (:placeID)");
			query.setParameterList("placeID",placeList);
			requestList=query.list();
			
//			for(int i=0;i<requestList.size();i++)
//			{
//				System.out.println("elements:"+requestList.get(i));
//			}
			//Collection k = null;
			
			
			//requestList=query.list();
			
			
			
			commit();
			
		}
		catch (HibernateException e) {
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return requestList;

}
	
	
	
	
	public List<TravellerHostRequest> getTravellerRequestList(long requestID)throws ParseException{
		
		List<TravellerHostRequest> requestT=null;
		try{
			begin();
			
			Query query=getSession().createQuery("from TravellerHostRequest where requestID = :k");
			query.setLong("k",requestID);
			requestT=query.list();
			
//			for(int i=0;i<requestList.size();i++)
//			{
//				System.out.println("elements:"+requestList.get(i));
//			}
			//Collection k = null;
			
			
			//requestList=query.list();
			
			
			
			commit();
			
		}
		catch (HibernateException e) {
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return requestT;
		
		
		
	}
	
	
	public List<TravellerHostRequest> getPendingList(List<TravellerHostRequest> requestList)throws ParseException{
		
		System.out.println("elements inside dao :");
		List<TravellerHostRequest> request1List=null;
		try{
			begin();
			
			
			for(TravellerHostRequest rt:requestList){
				
				Query query=getSession().createQuery("from TravellerHostRequest where status = :k");
				query.setParameter("k","pending");
				request1List=query.list();
				
				
				
			}
			
			if(request1List.isEmpty()){
				
				
			}
			
			
			
//			for(int i=0;i<requestList.size();i++)
//			{
//				System.out.println("elements:"+requestList.get(i));
//			}
			//Collection k = null;
			
			
			//requestList=query.list();
			
			
			
			commit();
			
		}
		catch (HibernateException e) {
			rollback();
			try {
				System.out.println("you already have an entry for this request");
			//	return new ModelAndView("requestAccepted");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return request1List;

}
	
	
	
}