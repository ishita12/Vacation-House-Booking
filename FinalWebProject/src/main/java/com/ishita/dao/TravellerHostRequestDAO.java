package com.ishita.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ishita.exceptions.UserException;
import com.ishita.pojos.PaymentDetails;
import com.ishita.pojos.Places;
import com.ishita.pojos.TravellerHostRequest;

public class TravellerHostRequestDAO extends DAO{

	
	public TravellerHostRequest register(TravellerHostRequest th)throws ParseException{
		
		begin();

		
		getSession().save(th);
		commit();
		return th;

	
}
	
	
	public PaymentDetails registerPayment(PaymentDetails pp)throws ParseException{
		begin();
		
		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
	       pp.setPaymentDate(sqlDate);
		getSession().save(pp);
		commit();
		return pp;
	}
	
	
	
	
	
	public List<TravellerHostRequest> getStatus(long tid)throws ParseException{
		
		List<TravellerHostRequest> tlist=null;
		try{
		begin();
		Query query=getSession().createQuery("from TravellerHostRequest where travelId =:t");
		
		query.setLong("t",tid);
		
		
		tlist=query.list();
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
return tlist;
		
	}
	
	public List<PaymentDetails>getPaidRequest(long pid)throws ParseException{
		
		List<PaymentDetails>ptList=null;
		try{
			
			
			begin();
			Query query=getSession().createQuery("from PaymentDetails where paymentID =:t");
			query.setLong("t",pid);
			
			ptList=query.list();
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
		return ptList;
		
		
	}
	
	
	
	
	public int getUpdatedList(long tpid,String st)throws ParseException{

		List<TravellerHostRequest>ltList=null;
		int result=0;
		try{
			
			
			begin();
			Query query=getSession().createQuery("update TravellerHostRequest set status = :st" +
    				" where requestID = :t");
			query.setLong("t",tpid);
			query.setString("st", "Approve");
			result = query.executeUpdate();
			//ltList=query.list();
			commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
		return result;
	}
	
	
	public int getCancelList(long tpid1,String st1)throws ParseException{

		List<TravellerHostRequest>ltList=null;
		int result1=0;
		try{
			
			
			begin();
			Query query=getSession().createQuery("update TravellerHostRequest set status = :st1" +
    				" where requestID = :t");
			query.setLong("t",tpid1);
			query.setString("st1", "Cancelled");
			result1 = query.executeUpdate();
			//ltList=query.list();
			commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			try {
				throw new UserException("Could not get user with " + e);
			} catch (UserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
		return result1;
	}
	
	
	
	
	
	
	public List<TravellerHostRequest> getTravellerList(long ttrid)throws ParseException{
		
		List<TravellerHostRequest>ttList=null;
		try{
			
			
			begin();
			Query query=getSession().createQuery("from TravellerHostRequest where requestID =:t");
			query.setLong("t",ttrid);
			
			ttList=query.list();
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
		return ttList;
	}
	
public List<TravellerHostRequest> getTraList(long tid)throws ParseException{
		
		List<TravellerHostRequest>ttList=null;
		try{
			
			
			begin();
			Query query=getSession().createQuery("from TravellerHostRequest where travelId =:t");
			query.setLong("t",tid);
			
			ttList=query.list();
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
		return ttList;
	}
	
	
	
	
	
	
	
	
	
	
	
	public List<TravellerHostRequest> getTravellerRequest(long tid)throws ParseException{
		List<TravellerHostRequest> tlist=null;
		try{
		begin();
		Query query=getSession().createQuery("from TravellerHostRequest where travelId =:t");
		
		query.setLong("t",tid);
		
		
		tlist=query.list();
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
return tlist;
	}
	public List<Places> getList(String placeName)throws ParseException{
		List<Places> placeList=null;
		try {
		begin();
		
		Query query = getSession().createQuery("from Places where placeName = :k");
		query.setString("k",placeName);	
		//query.setString("p",placeName);	
		placeList =query.list();
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
	
//public Places edit(Places p) throws Exception{
//		
//		try {
//			begin();
//			System.out.println("inside DAO");
//
//			
//			
//         
//			
////		place.setPlaceName(p.getPlaceName());
////            place.setRent(p.getRent());			
////			place.setFile_name(p.getFile_name());
//			getSession().update(p);
//			commit();
//			
//
//		} catch (HibernateException e) {
//			rollback();
//			throw new Exception("Exception while adding places: " + e.getMessage());
//		}
//	
//	return p;
//	}
//	public List<Places> deleteList(long placeID,long travelId){
//		
//		
//		
//		List<Places> placeList=null;
//		try {
//		begin();
//		
//		Query query = getSession().createQuery("delete TravellerHostRequest where placeID = :k and travelId = :d");
//		query.setLong("k",placeID);	
//		query.setLong("d",travelId);
//		//query.setString("p",placeName);	
//		query.executeUpdate();
//		placeList =query.list();
//		commit();
//		
//	} catch (HibernateException e) {
//		rollback();
//		try {
//			throw new UserException("Could not get user with " + e);
//		} catch (UserException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//		return placeList;
//		
//		
//		
//	}
	

public List<TravellerHostRequest> gettList(long placeID,long travelId)throws ParseException{
	List<TravellerHostRequest> tList=null;
	try {
	begin();
	
	Query query = getSession().createQuery("from TravellerHostRequest where placeID = :k and travelId = :d");
	query.setLong("k",placeID);	
	query.setLong("d",travelId);	
	//query.setString("p",placeName);	
	tList =query.list();
	commit();
//	System.out.println("id and date is"+ tList.get(2)+tList.get(5));
	
} catch (HibernateException e) {
	rollback();
	try {
		throw new UserException("Could not get user with " + e);
	} catch (UserException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	return tList;
	
}

public TravellerHostRequest updateRecord(TravellerHostRequest t){
	
	
	
	begin();
	getSession().update(t);
	commit();
	
	return t;
}

	
	
}
