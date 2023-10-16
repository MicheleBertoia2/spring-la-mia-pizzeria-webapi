package org.java.lessons.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza)
	{
		pizzaRepo.save(pizza);
	}
	
	public List<Pizza> findAll()
	{
		return pizzaRepo.findAll();
	}
	
	public Optional<Pizza> findById(int id)
	{
		return pizzaRepo.findById(id);
	}
	
	public List<Pizza> searchByName(String name)
	{
		return pizzaRepo.findByNameContaining(name);
	}
	
	public void deletePizza(Pizza pizza)
	{
		pizzaRepo.delete(pizza);
	}
}
