package org.java.lessons.mvc.controller;

import java.util.List;

import org.java.lessons.db.pojo.Ingredient;
import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.pojo.SpecialOffer;
import org.java.lessons.db.serv.IngredientService;
import org.java.lessons.db.serv.PizzaService;
import org.java.lessons.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService sos;
	
	@Autowired
	private IngredientService ise;

	@GetMapping
	public String index(Model model,
						@RequestParam(required = false) String name
						)
	{
		
		List<Pizza> pizze = name == null
							? pizzaService.findAll()
							: pizzaService.searchByName(name);
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("name", name);
		return "pizza-index";
	}
	
	@GetMapping("/pizze/{id}")
	public String show(Model model, @PathVariable int id)
	{
		Pizza pizza = pizzaService.findById(id).get();
		model.addAttribute("pizza", pizza);
		return "pizza-show";
	}
	
	@GetMapping("/create")
	public String pizzaCreate(Model model)
	{
		List<Ingredient> ingredients = ise.findAll();		
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", new Pizza());
		
		return "pizza-create";
	}
	
	@PostMapping("/create")
	public String pizzaStore(
				@Valid @ModelAttribute Pizza pizza,
				BindingResult bindingResult,
				Model model
			) {
		if(bindingResult.hasErrors())
		{
			System.out.println("Error:");
			
			bindingResult.getAllErrors().forEach(System.out::println);
			return "pizza-create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable int id, Model model)
	{
		Pizza pizza = pizzaService.findById(id).get();
		List<Ingredient> ingredients = ise.findAll();		
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", pizza);
		
		return "pizza-update";
	}
	
	@PostMapping("/edit/{id}")
	public String updatePizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult br,
			Model model)
	{
		if(br.hasErrors())
		{
			br.getAllErrors().forEach(System.out::println);
			return "pizza-update";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id)
	{
		Pizza pizza = pizzaService.findById(id).get();
		pizzaService.deletePizza(pizza);
		return "redirect:/";
	}
	
	//Special Offers
	@GetMapping("/pizze/offers/{pizza_id}")
	public String createOffer(
			@PathVariable("pizza_id") int id,
			Model model
			)
	{
		Pizza pizza = pizzaService.findById(id).get();
		SpecialOffer specialOffer = new SpecialOffer();
		
		model.addAttribute("specialOffer", specialOffer);
		model.addAttribute("pizza", pizza);
		
		return "offer-create";
	}
	
	@PostMapping("/pizze/offers/{pizza_id}")
	public String storeOffer(
			@Valid @ModelAttribute SpecialOffer specialOffer,
			BindingResult br,
			@PathVariable("pizza_id") int id,
			Model model
			)
	{
		Pizza pizza = pizzaService.findById(id).get();
		specialOffer.setPizza(pizza);
		sos.save(specialOffer);
		return "redirect:/pizze/" + id;
	}
	
	@PostMapping("/offers/delete/{offer_id}")
	public String deleteOffer(@PathVariable("offer_id") int id)
	{
		SpecialOffer specialOffer = sos.findById(id);
		Pizza pizza = specialOffer.getPizza();
		sos.delete(specialOffer);
		return "redirect:/pizze/" + pizza.getId();
	}
	
	@GetMapping("/offers/edit/{offer_id}")
	public String editOffer(
			@PathVariable("offer_id") int id,
			Model model
			)
	{
		SpecialOffer spOf = sos.findById(id);
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("specialOffer", spOf);
		model.addAttribute("pizze", pizze);
		
		return "offer-edit";
	}
	
	@PostMapping("/offers/edit/{offer_id}")
	public String updateOffer(
			@Valid @ModelAttribute SpecialOffer specialOffer,
			BindingResult br,
			Model model
			)
	{
		sos.save(specialOffer);
		
		Pizza pizza = specialOffer.getPizza();
		
		return "redirect:/pizze/" + pizza.getId();
	}
	
}
