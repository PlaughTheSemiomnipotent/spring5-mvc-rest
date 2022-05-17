package guru.springframework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;

public class CustomerServiceTest {
	public static final Long ID = 2L;
	public static final String LASTNAME = "Rubble";
	public static final String FIRSTNAME = "Barney";
	
	CustomerService customerService;
	CustomerMapper customerMapper;
	
	@Mock
	CustomerRepository customerRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}
	
	@Test
	public void getAllCustomers() throws Exception {
		//given
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
		
		//when
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
		
		//then
		assertEquals(3, customerDTOs.size());
	}
	
	@Test
	public void getCustomerById() throws Exception {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Michael");
        customer1.setLastName("Weston");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Michael", customerDTO.getFirstName());
	}
	
	@Test
	public void createNewCustomer() throws Exception {
		//given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Jim");
		customerDTO.setLastName("Brown");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);
		
		//when
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);
		
		//then
		assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
		assertEquals(customerDTO.getLastName(), savedDto.getLastName());
		assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
	}
	
	@Test
	public void saveCustomerByDTO() throws Exception {
		//given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Jim");
		customerDTO.setLastName("Brown");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);

		//when
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);
		
		//then
		assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
		assertEquals(customerDTO.getLastName(), savedDto.getLastName());
		assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
		
	}
}
