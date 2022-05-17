package guru.springframework.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;

public class CustomerMapperTest {
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;
	final static String FNAME = "Fred";
	final static String LNAME = "Flintstone";
	final static Long ID = 1L;

	@Test
	public void customerToCustomerDTO() throws Exception {
		//given
		Customer customer = new Customer();
		
		customer.setId(ID);
		customer.setFirstName(FNAME);
		customer.setLastName(LNAME);
		
		//when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		
		//then
		assertEquals(ID, customerDTO.getId());
		assertEquals(FNAME, customerDTO.getFirstName());
		assertEquals(LNAME, customerDTO.getLastName());
	}
}
