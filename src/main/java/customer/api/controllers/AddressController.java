package customer.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import customer.api.transformer.JsonTransform;

public class AddressController {

	public AddressController(ObjectMapper objectMapper, JsonTransform jsonTransform) {
		setup(objectMapper,jsonTransform);
	}

	private void setup(ObjectMapper objectMapper, JsonTransform jsonTransform) {

		
	}

}
