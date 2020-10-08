package customer.api.controllers;

import static spark.Spark.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import customer.api.models.Customer;
import customer.api.models.Gender;
import customer.api.transformer.JsonTransform;

public class CustomerController {

	public CustomerController() {
		
		setup();
	}

	private void setup() {
		 
		 get("/customer", "application/json", (request, response) -> {
			 Customer customer =  new Customer();
			 customer.setId(1);
			 customer.setCpf("93743098407");
			 customer.setGender(Gender.MASCULINO);
			 customer.setEmail("desenvolvimento@tce.ma.gov.br");
			    return customer;
			}, new JsonTransform());
		 
		 
		 
		 
		 get("/customer/:id", "application/json", (request, response) -> {
			 
			 
			 
			 Customer customer =  new Customer();
			 customer.setId(1);
			 customer.setCpf("93743098407");
			 customer.setGender(Gender.MASCULINO);
			 customer.setEmail("desenvolvimento@tce.ma.gov.br");
			    return customer;
			}, new JsonTransform());
		 
		 
		 
		 
		 
		 post("/posts", (request, response) -> {
		        ObjectMapper mapper = new ObjectMapper();
		       /* NewPostPayload creation = mapper.readValue(request.body(), NewPostPayload.class);
		        if (!creation.isValid()) {
		            response.status(HTTP_BAD_REQUEST);
		            return "";
		        }
		        UUID id = model.createPost(creation.getTitle(), creation.getContent(), creation.getCategories());*/
		        response.status(200);
		        response.type("application/json");
		        return id;
		    });
		 
		 
		
		
	}
	
	
	
	

}
