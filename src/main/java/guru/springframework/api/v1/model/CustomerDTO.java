package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Created by jt on 9/24/17.
 */
@Data
public class CustomerDTO {
	private Long id;
	private String firstName;
	private String lastName;
	
	@JsonProperty("customer_url")
	private String customerUrl;
}
