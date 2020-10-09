package customer.api.di;

import org.jdbi.v3.core.Jdbi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import customer.api.Application;
import customer.api.transformer.JsonTransform;

public class GuiceModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(Application.class).in(Singleton.class);
	}

	@Provides
	@Singleton
	private ObjectMapper provideObjectMapper() {
		return new ObjectMapper();
	}
	
	@Provides
	@Singleton
	private JsonTransform provideJsonTransform() {
		return new JsonTransform();
	}
	
	@Provides
	@Singleton
	private Jdbi getJdbi() {
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/customers_db","root","root");
		return jdbi;
	}

}
