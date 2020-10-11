package customer.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;

import customer.api.controllers.CustomerController;
import customer.api.di.GuiceModule;
import customer.api.exceptions.BusinessException;
import customer.api.transformer.JsonTransform;

import static spark.Spark.*;

public class Application {


	private final ObjectMapper objectMapper;
	private final JsonTransform jsonTransform;

	@Inject
	public Application(final ObjectMapper objectMapper,final JsonTransform jsonTransform) {
		super();
		this.objectMapper = objectMapper;
		this.jsonTransform = jsonTransform;
	}

	void run(){
		port(8080);
		new CustomerController(objectMapper,jsonTransform);
		
		exception(BusinessException.class, (exception, request, response) -> {
			response.status(400);
			response.body(exception.getMessage());
		});
	}

	public static void main(String[] args) {
		Guice.createInjector(new GuiceModule())
		.getInstance(Application.class)
		.run();    
	}


}
