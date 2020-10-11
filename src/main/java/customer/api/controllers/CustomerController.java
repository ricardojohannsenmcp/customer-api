package customer.api.controllers;

import static spark.Spark.*;


import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import customer.api.di.GuiceModule;
import customer.api.models.Address;
import customer.api.models.Customer;
import customer.api.models.form.CustomerForm;
import customer.api.repository.AddressRepository;
import customer.api.services.ICustomerService;
import customer.api.transformer.JsonTransform;

public class CustomerController {

	private ICustomerService customerService;
	
	@Inject
	private AddressRepository addressRepository;

	public CustomerController(ObjectMapper objectMapper, JsonTransform jsonTransform) {

		Injector injector = Guice.createInjector(new GuiceModule());
		customerService = injector.getInstance(ICustomerService.class);
		setup(objectMapper,jsonTransform);
	}

	private void setup(ObjectMapper objectMapper, JsonTransform jsonTransform) {
		
		
		post("/customer", (request, response) -> {
			CustomerForm customerForm = objectMapper.readValue(request.body(), CustomerForm.class);
			//if (!creation.isValid()) {
			//   response.status(HTTP_BAD_REQUEST);
			//   return "";
			//  }

			Customer customer = customerForm.toCustomer();
			Address address = customerForm.getAddress();
			Customer saved = customerService.save(customer,address);
			response.status(200);
			response.type("application/json");
			return saved;
		}, jsonTransform);
		

		get("/customer", "application/json", (request, response) -> {
			List<Customer> customers =  customerService.findAll();
			return customers;
		}, jsonTransform);



		get("/customer/:id", "application/json", (request, response) -> {
			Integer id = Integer.valueOf(request.params("id"));
			if(id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			Customer customer =  customerService.findByPrimaryKey(id);
			return customer;
		}, jsonTransform);
		
		

		delete("/customer/:id",(request, response) -> {
			Integer id = Integer.valueOf(request.params("id"));
			if(id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			customerService.remove(id);
			response.status(200);
			return "";
		});
		
		
		post("/customer/:id/address", "application/json", (request, response) -> {
			Integer id = Integer.valueOf(request.params("id"));
			if(id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			Customer customer = customerService.findByPrimaryKey(id);
			Address address = objectMapper.readValue(request.body(), Address.class);
			return customerService.saveAddress(customer,address);
		}, jsonTransform);
		
		
		get("/customer/:id/address", "application/json", (request, response) -> {
			Integer id = Integer.valueOf(request.params("id"));
			if(id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			List<Address> addresses = customerService.addressesFromCustomer(id);
			return addresses;
		}, jsonTransform);
		
		
		

		put("/customer/:id/address/:address_id", "application/json", (request, response) -> {
			Integer id = Integer.valueOf(request.params("id"));
			if(id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			
			Integer adress_id = Integer.valueOf(request.params("address_id"));
			if(adress_id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			
			Customer customer = customerService.findByPrimaryKey(id);
			
			Address adressToUpdate = addressRepository.findByPrimaryKey(adress_id);
			
			
			Address address = objectMapper.readValue(request.body(), Address.class);
			return customerService.saveAddress(customer,address);
		}, jsonTransform);
		
		
		
		
		delete("/customer/:id/address/:address_id", "application/json", (request, response) -> {
			Integer id = Integer.valueOf(request.params("id"));
			if(id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			Integer adress_id = Integer.valueOf(request.params("address_id"));
			if(adress_id == null) {
				response.status(HttpStatus.BAD_REQUEST_400);
				return ""; 
			}
			customerService.removeAddressFromCustomer(id,adress_id);
			
			response.status(200);
			return "";
			});
			
			
	
		
		
		
	





	}





}
