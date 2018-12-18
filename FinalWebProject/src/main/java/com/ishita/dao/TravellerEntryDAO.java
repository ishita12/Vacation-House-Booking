package com.ishita.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ishita.exceptions.UserException;
import com.ishita.pojos.Places;
import com.ishita.pojos.TravellerEntry;


public class TravellerEntryDAO extends DAO{
	public TravellerEntry register(TravellerEntry t)throws ParseException{
		
			begin();
//			System.out.println("inside DAO: " + t.getUserEntry().getFirstName());

			//System.out.println(t.getCity()+t.getTravellerEntryID());
			//TravellerEntry travel = new TravellerEntry();
              
			//travel.setCity(t.getCity());
			
		//travel.setUserEntry(t.);
			
			
//			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//			String startDate=format.format(t.getsDate());
//			String endDate=format.format(t.geteDate());
//			t.setsDate(format.parse(startDate));
//			t.setsDate(format.parse(endDate));
			
			
			getSession().save(t);
			commit();
			return t;

		
	}
	
	
	
	
	
	
	
	
	public List<Places> get(String key) throws UserException {
		try {
			begin();
			
			Query query = getSession().createQuery("from Places where placeName = :k ");
			query.setString("k", key);		
			List<Places> placeList =query.list();
			commit();
			return placeList;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user with " + key, e);
		}
	}

	
}
