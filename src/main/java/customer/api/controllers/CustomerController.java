package customer.api.controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import customer.api.di.GuiceModule;
import customer.api.models.Address;
import customer.api.models.Customer;
import customer.api.models.CustomerFilter;
import customer.api.models.form.CustomerForm;
import customer.api.repository.IAdressRepository;
import customer.api.services.ICustomerService;
import customer.api.transformer.JsonTransform;

public class CustomerController {

	private ICustomerService customerService;
	
	private IAdressRepository addressRepository;

	public CustomerController(ObjectMapper objectMapper, JsonTransform jsonTransform) {

		Injector injector = Guice.createInjector(new GuiceModule());
		customerService = injector.getInstance(ICustomerService.class);
		addressRepository =  injector.getInstance(IAdressRepository.class);
		setup(objectMapper,jsonTransform);
	}

	private void setup(ObjectMapper objectMapper, JsonTransform jsonTransform) {
		
		post("/customers", (request, response) -> {
			CustomerForm customerForm = objectMapper.readValue(request.body(), CustomerForm.class);
			Customer customer = customerForm.toCustomer();
			Address address = customerForm.getAddress();
			Customer saved = customerService.save(customer,address);
			response.status(200);
			response.type("application/json");
			return saved;
		}, jsonTransform);
		

		get("/customers", "application/json", (request, response) -> {
			List<Customer> customers =  customerService.findAll(new CustomerFilter(request));
			return customers;
		}, jsonTransform);



		get("/customers/:id", "application/json", (request, response) -> {
			Customer customer =  customerService.findByPrimaryKey(Integer.valueOf(request.params("id")));
			return customer;
		}, jsonTransform);
		
		put("/customers/:id", (request, response) -> {
			Customer originalCustomer = customerService.findByPrimaryKey(Integer.valueOf(request.params("id")));
			CustomerForm customerForm = objectMapper.readValue(request.body(), CustomerForm.class);
			Customer customer = customerForm.toCustomer();
			Address address = customerForm.getAddress();
			Customer saved = customerService.update(customer,originalCustomer,address);
			response.status(200);
			response.type("application/json");
			return saved;
		}, jsonTransform);
		
		

		delete("/customers/:id",(request, response) -> {
			customerService.remove(Integer.valueOf(request.params("id")));
			response.status(200);
			return "";
		});
		
		
		post("/customers/:id/address", "application/json", (request, response) -> {
			Customer customer = customerService.findByPrimaryKey(Integer.valueOf(request.params("id")));
			Address address = objectMapper.readValue(request.body(), Address.class);
			return customerService.saveAddress(customer,address);
		}, jsonTransform);
		
		
		get("/customers/:id/address", "application/json", (request, response) -> {
			List<Address> addresses = customerService.addressesFromCustomer(Integer.valueOf(request.params("id")));
			return addresses;
		}, jsonTransform);
		
		
		get("/customers/:id/address/:address_id", "application/json", (request, response) -> {
			Customer customer =  customerService.findByPrimaryKey(Integer.valueOf(request.params("id")));
			Address address = addressRepository.findByPrimaryKey(Integer.valueOf(request.params("address_id")));
			address.checkbelongsTo(customer);
			return address;
		}, jsonTransform);
		
		

		put("/customers/:id/address/:address_id", "application/json", (request, response) -> {
			Customer customer = customerService.findByPrimaryKey(Integer.valueOf(request.params("id")));
			Address addressToUpdate = addressRepository.findByPrimaryKey(Integer.valueOf(request.params("address_id")));
			Address address = objectMapper.readValue(request.body(), Address.class);
			return customerService.updateAddressFromCustomer(customer, address, addressToUpdate);
		}, jsonTransform);
		
		
		
		
		delete("/customers/:id/address/:address_id", "application/json", (request, response) -> {
			Customer customer = customerService.findByPrimaryKey(Integer.valueOf(request.params("id")));
			Address address = addressRepository.findByPrimaryKey(Integer.valueOf(request.params("address_id")));
			customerService.removeAddressFromCustomer(customer,address);
			response.status(200);
			return "";
			});
			
	
	}

}
