package org.java.lessons.db.pojo;


import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.java.lessons.api.dto.PizzaDTO;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column( length = 128)
	@Length (
				min = 3,
				max = 128,
				message = "il nome deve contenere 3-128 caratteri"
			)
	private String name;
	
	@Min(0)
	private double price;
	
	private String image;
	
	
	@Column(columnDefinition = "text")
	private String ingredients;
	
	@OneToMany(mappedBy = "pizza")
	@JsonManagedReference
	private List<SpecialOffer> specialOffers;
	
	@ManyToMany
	@JsonManagedReference
	private List<Ingredient> ingredienti;
	
	public Pizza() {}
	public Pizza(String name, double price, String image, String ingredients, Ingredient...ingredients2 )
	{
		setName(name);
		setPrice(price);
		setImage(image);
		setIngredients(ingredients);
		setIngredienti(Arrays.asList(ingredients2));
	}
	
	public Pizza(PizzaDTO pizzaDto) {
		
		setName(pizzaDto.getName());
		
		setImage(pizzaDto.getImage());
	}
	
	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}
	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<Ingredient> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingredient> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public boolean hasIngredient(Ingredient ingredient)
	{
		if(getIngredienti() == null) return false;
		
		for(Ingredient i : getIngredienti())
		{
			if(ingredient.getId() == i.getId())
				return true;
		}
		
		return false;
	}
	
	public void removeIngredient(Ingredient ingredient)
	{
		getIngredienti().remove(ingredient);
	}
	
	public void fillFromDto(PizzaDTO pizzaDto)
	{
		setName(pizzaDto.getName());
		setPrice(pizzaDto.getPrice());
		setImage(pizzaDto.getImage());
	}
	
	@Override
	public String toString() {
		
		return "[id: " + getId() + "]\n"
				+ "nome: " + getName() + "\n"
				+ "prezzo: " + String.format("%.2d", getPrice()) + "\n"
				+ "ingredienti: " + getIngredients();
	}
}
