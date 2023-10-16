package org.java.lessons.api.dto;

public class PizzaDTO {





		private int id;
		

		private String name;
		

		private double price;
		
		private String image;
		
		

		private String ingredients;
		

		public PizzaDTO() {}
		
		public PizzaDTO(String name)
		{
			setName(name);
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

		

		
		@Override
		public String toString() {
			
			return "[id: " + getId() + "]\n"
					+ "nome: " + getName() + "\n"
					+ "prezzo: " + getPrice() + "\n"
					+ "ingredienti: " + getIngredients();
		}
	}

