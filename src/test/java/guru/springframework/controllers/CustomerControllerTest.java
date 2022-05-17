package guru.springframework.controllers;

import static guru.springframework.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.controllers.v1.CustomerController;
import guru.springframework.services.CustomerService;

public class CustomerControllerTest {
	public static final Long ID = 1L;
	
	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController customerController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void testListCustomers() throws Exception {
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setId(1L);
		customer1.setFirstName("Fred");
		
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setId(2L);
		customer2.setFirstName("Barney");
		
		List<CustomerDTO> customers = Arrays.asList(customer1, customer2);
		
		when(customerService.getAllCustomers()).thenReturn(customers);
		
		mockMvc.perform(get("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", hasSize(2)));
		
	}
	
	@Test
	public void testGetCustomerById() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michael");
        customer1.setLastName("Weston");
        customer1.setCustomerUrl("/api/v1/customer/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        //when
        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")));
	}

	@Test
	public void createNewCustomer() throws Exception {
		//given
		CustomerDTO customer =  new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		//when
		when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);
		
		//then
		mockMvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", equalTo("Fred")))
			.andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}
	
	@Test
	public void updateCustomer() throws Exception {
		//given
		CustomerDTO customer =  new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		//when
		when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);
		
		//then
		mockMvc.perform(put("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", equalTo("Fred")))
			.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
			.andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
	}
}
