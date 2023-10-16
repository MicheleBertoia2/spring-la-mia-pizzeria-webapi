package org.java.lessons.mvc.controller;

import java.util.List;

import org.java.lessons.db.pojo.Ingredient;
import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.serv.IngredientService;
import org.java.lessons.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ise;
	
	@Autowired
	private PizzaService pse;
	
	@GetMapping
	public String index(Model model)
	{
		List<Ingredient> ingredients = ise.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "ingredients-index";
	}
	
	@GetMapping("/create")
	public String createIngredient(Model model)
	{
		model.addAttribute("ingredient", new Ingredient());
		return "ingredients-create";
	}
	
	@PostMapping("/create")
	public String storeIngredient(
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult br,
			Model model
			)
	{
		if(br.hasErrors())
			return "ingredients-create";
		
		ise.save(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@PostMapping("/delete/{ing_id}")
	public String delete(
				@PathVariable("ing_id") int id
			)
	{
		Ingredient ingredient = ise.findById(id);
		List<Pizza> pizze = pse.findAll();
		
		for(Pizza p : pizze)
		{
			if(p.hasIngredient(ingredient))
				p.removeIngredient(ingredient);
		}
		
		ise.delete(ingredient);
		
		return "redirect:/ingredients";
	}
}







