package org.java.lessons.db.serv;

import java.util.List;

import org.java.lessons.db.pojo.Ingredient;
import org.java.lessons.db.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	IngredientRepo ire;
	
	public List<Ingredient> findAll()
	{
		return ire.findAll();
	}
	
	public Ingredient findById(int id)
	{
		return ire.findById(id).get();
	}
	
	public void save(Ingredient ingredient)
	{
		ire.save(ingredient);
	}
	
	public void delete(Ingredient ingredient)
	{
		ire.delete(ingredient);
	}
}
