package customer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;

import customer.api.controllers.CustomerController;
import customer.api.di.GuiceModule;
import customer.api.transformer.JsonTransform;

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
		new CustomerController(objectMapper,jsonTransform);
	}

	public static void main(String[] args) {
		Guice.createInjector(new GuiceModule())
		.getInstance(Application.class)
		.run();    
	}


}
