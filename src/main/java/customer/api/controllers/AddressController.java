package customer.api.controllers;

import static spark.Spark.get;

import com.fasterxml.jackson.databind.ObjectMapper;

import customer.api.models.Customer;
import customer.api.models.Gender;
import customer.api.transformer.JsonTransform;

public class AddressController {

	public AddressController(ObjectMapper objectMapper, JsonTransform jsonTransform) {
		setup(objectMapper,jsonTransform);
	}

	private void setup(ObjectMapper objectMapper, JsonTransform jsonTransform) {

		get("/address", "application/json", (request, response) -> {
			Customer customer =  new Customer();
			customer.setId(1);
			customer.setCpf("93743098407");
			customer.setGender(Gender.MASCULINO);
			customer.setEmail("desenvolvimento@tce.ma.gov.br");
			return customer;
		}, jsonTransform);
	}

}
