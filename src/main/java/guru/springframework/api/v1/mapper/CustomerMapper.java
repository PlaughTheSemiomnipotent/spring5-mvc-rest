package guru.springframework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	@Mapping(source = "id", target = "id")
	CustomerDTO customerToCustomerDTO(Customer customer);

	Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
