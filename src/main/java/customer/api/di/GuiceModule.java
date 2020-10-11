package customer.api.di;

import org.jdbi.v3.core.Jdbi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import customer.api.Application;
import customer.api.repository.AddressRepository;
import customer.api.repository.CustomerRepository;
import customer.api.repository.IAdressRepository;
import customer.api.repository.ICustomerRepository;
import customer.api.services.CustomerService;
import customer.api.services.ICustomerService;
import customer.api.transformer.JsonTransform;

public class GuiceModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(Application.class).in(Singleton.class);
		bind(IAdressRepository.class).to(AddressRepository.class).asEagerSingleton();
		bind(ICustomerRepository.class).to(CustomerRepository.class).asEagerSingleton();
		bind(ICustomerService.class).to(CustomerService.class).asEagerSingleton();

	}

	@Provides
	@Singleton
	private ObjectMapper provideObjectMapper() {
		return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@Provides
	@Singleton
	private JsonTransform provideJsonTransform() {
		return new JsonTransform();
	}
	
	@Provides
	@Singleton
	private Jdbi getJdbi() {
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/customer_db","root","root");
		return jdbi;
	}

}
