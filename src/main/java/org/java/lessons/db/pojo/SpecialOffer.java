package org.java.lessons.db.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	
	@PastOrPresent
	private LocalDate startDate;
	
	
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private Pizza pizza;
	
	
	public SpecialOffer() {}
	public SpecialOffer(String title, LocalDate startDate, LocalDate endDate, Pizza pizza)
	{
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setPizza(pizza);
	}
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getHtmlStartDate() {

		return getStartDate() == null
				? null
				: getStartDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	public void setHtmlStartDate(String date) {

		setStartDate(LocalDate.parse(date));
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getHtmlEndDate() {

		return getEndDate() == null
				? null
				: getEndDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	public void setHtmlReturnDate(String date) {

		setEndDate(LocalDate.parse(date));
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] start:" + getStartDate() + " ~ end:" + getEndDate() 
		+ "\nTitolo:\n" + getTitle();
	}
}
