package org.java.lessons.db.serv;

import java.util.List;

import org.java.lessons.db.pojo.SpecialOffer;
import org.java.lessons.db.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

	@Autowired
	SpecialOfferRepo sor;
	
	public List<SpecialOffer> findAll()
	{
		return sor.findAll();
	}
	
	public SpecialOffer findById(int id)
	{
		return sor.findById(id).get();
	}
	
	public void save(SpecialOffer specialOffer)
	{
		sor.save(specialOffer);
	}
	
	public void delete(SpecialOffer specialOffer)
	{
		sor.delete(specialOffer);
		
	}
}
