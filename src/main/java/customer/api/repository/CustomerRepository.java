package customer.api.repository;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import com.google.inject.Inject;

import customer.api.models.Customer;
import customer.api.row.mapper.CustomerRowMapper;

public class CustomerRepository {
	
	@Inject
	private Jdbi jdbi;
	
	
	public Customer save(Customer customer) {
		 Integer generatedId =  null;
		try(Handle handle = jdbi.open()){
			 generatedId = handle.createUpdate("insert into customer (uuid,cpf,email,birth_date,gender) values (:uuid,:cpf,:email,:birth,:gender) ")
			.bind("uuid", customer.getUuid())
			.bind("cpf",customer.getCpf())
			.bind("email", customer.getEmail())
			.bind("birth", customer.getBirthDate())
			.bind("gender", customer.getGender()).executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one();
		}
		 return findByPrimaryKey(generatedId);
	}
	
	
	private Customer findByPrimaryKey(Integer id) {
		try(Handle handle = jdbi.open()){
		Optional<Customer> optional = handle.createQuery("SELECT c.id,uuid,c.cpf,c.email,c.gender,c.birth_date from customer c where c.id = :id ")
		.bind("id", id)
		.map(new CustomerRowMapper()).findOne();
		return optional.isPresent() ? optional.get() : null;
		}
	}


	public List<Customer> findAll(){
		try(Handle handle = jdbi.open()){
		List<Customer> customers = handle.createQuery("SELECT id, name FROM user ORDER BY id ASC").map(new CustomerRowMapper()).list();
			return customers;
		}
	}
	
	
	
	
	
	
	
	

}
