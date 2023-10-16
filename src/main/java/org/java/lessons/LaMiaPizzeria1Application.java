package org.java.lessons;

import java.time.LocalDate;

import org.java.lessons.db.pojo.Ingredient;
import org.java.lessons.db.pojo.Pizza;
import org.java.lessons.db.pojo.SpecialOffer;
import org.java.lessons.db.serv.IngredientService;
import org.java.lessons.db.serv.PizzaService;
import org.java.lessons.db.serv.SpecialOfferService;
import org.java.lessons.mvc.auth.pojo.Role;
import org.java.lessons.mvc.auth.pojo.User;
import org.java.lessons.mvc.auth.service.RoleService;
import org.java.lessons.mvc.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LaMiaPizzeria1Application implements CommandLineRunner{
	
	@Autowired
	private PizzaService pServ;
	
	@Autowired
	private SpecialOfferService sor;
	
	@Autowired
	private IngredientService ise;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(LaMiaPizzeria1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		
		Ingredient i1 = new Ingredient("pomodoro");
		Ingredient i2 = new Ingredient("mozzarella");
		Ingredient i3 = new Ingredient("salamino");
		Ingredient i4 = new Ingredient("prosciutto");
		Ingredient i5 = new Ingredient("funghi");
		Ingredient i6 = new Ingredient("carciofi");
		
		ise.save(i1);
		ise.save(i2);
		ise.save(i3);
		ise.save(i4);
		ise.save(i5);
		ise.save(i6);
		
		Pizza pizza1 = new Pizza("margherita", 6.50d,
								"https://it.ooni.com/cdn/shop/articles/Margherita-9920.jpg?crop=center&height=800&v=1644590028&width=800", 
								"descrizione", i1, i2);
		
		Pizza pizza2 = new Pizza("capricciosa", 8.00d,
								"https://www.pizzanapoletanadoc.it/wp-content/uploads/2019/04/capricciosa-pizza-napoletana-doc.jpg" , 
								"descrizione", i1, i2, i4, i5, i6);
		
		Pizza pizza3 = new Pizza("diavola", 7.50d, 
								"https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_rustica.jpg?itok=Lbp_jtZW", 
								"descrizione", i1, i2, i3);
		
		pServ.save(pizza1);
		pServ.save(pizza2);
		pServ.save(pizza3);
		
		SpecialOffer offerta1 = new SpecialOffer("sconto20", LocalDate.parse("2023-01-16") , LocalDate.parse("2023-12-16"), pizza3);
		SpecialOffer offerta2 = new SpecialOffer("sconto30", LocalDate.parse("2023-02-25") , LocalDate.parse("2023-12-16"), pizza2);
		SpecialOffer offerta3 = new SpecialOffer("autunno caldo", LocalDate.parse("2023-01-16") , LocalDate.parse("2023-12-16"), pizza2);
		SpecialOffer offerta4 = new SpecialOffer("meta' prezzo", LocalDate.parse("2023-01-16") , LocalDate.parse("2023-12-16"), pizza1);
		SpecialOffer offerta5 = new SpecialOffer("voglia d'estate", LocalDate.parse("2023-01-16") , LocalDate.parse("2023-12-16"), pizza1);
		
		sor.save(offerta1);
		sor.save(offerta2);
		sor.save(offerta3);
		sor.save(offerta4);
		sor.save(offerta5);
		
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleService.save(admin);
		roleService.save(user);
		
		final String pwsAdmin = new BCryptPasswordEncoder().encode("pws");
		final String pwsUser = new BCryptPasswordEncoder().encode("pws");
		
		User mikiAdmin = new User("MikiAdmin", pwsAdmin, admin, user);
		User mikiUser = new User("MikiUser", pwsUser, user);
		
		userService.save(mikiAdmin);
		userService.save(mikiUser);
	}

}
