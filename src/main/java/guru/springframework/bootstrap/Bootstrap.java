package guru.springframework.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {
	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;
	
	@Autowired
	public void BootStrap(CategoryRepository categoryRepository,
			CustomerRepository customerRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		
		loadCustomers();
	}

	private void loadCategories() {
		Category fruits = new Category();
		fruits.setName("Fruits");
		
		Category dried = new Category();
		dried.setName("Dried");
		
		Category fresh = new Category();
		fresh.setName("Fresh");
		
		Category exotic = new Category();
		exotic.setName("Exotic");
		
		Category nuts = new Category();
		nuts.setName("Nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		
		System.out.println("Category Data Loaded = " + categoryRepository.count());
	}

	private void loadCustomers() {
		Customer cust1 = new Customer();
		cust1.setFirstName("Barney");
		cust1.setLastName("Rubble");
		
		Customer cust2 = new Customer();
		cust2.setFirstName("Betty");
		cust2.setLastName("Rubble");

		Customer cust3 = new Customer();
		cust3.setFirstName("Fred");
		cust3.setLastName("Flintstone");

		Customer cust4 = new Customer();
		cust4.setFirstName("Wilma");
		cust4.setLastName("Flintstone");
		
		customerRepository.save(cust1);
		customerRepository.save(cust2);
		customerRepository.save(cust3);
		customerRepository.save(cust4);
		
		System.out.println("Customer Data Loaded = " + customerRepository.count());
	}
}
