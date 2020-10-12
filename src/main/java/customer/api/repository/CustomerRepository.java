package customer.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import com.google.inject.Inject;

import customer.api.exceptions.BusinessException;
import customer.api.models.Customer;
import customer.api.row.mapper.CustomerRowMapper;



public class CustomerRepository implements ICustomerRepository{

	@Inject
	private Jdbi jdbi;



	public Customer save(Customer customer) {
		Integer generatedId =  null;
		try(Handle handle = jdbi.open()){
			generatedId = handle.createUpdate("insert into customer_db.customer (name,uuid,cpf,email,birth_date,gender,created_at,updated_at) values (:name,:uuid,:cpf,:email,:birth,:gender,:createdAt,:updatedAt) ")
					.bind("name", customer.getName())
					.bind("uuid", customer.getUuid())
					.bind("cpf",customer.getCpf())
					.bind("email", customer.getEmail())
					.bind("birth", customer.getBirthDate())
					.bind("createdAt", new Date())
					.bind("updatedAt", new Date())
					.bind("gender", customer.getGender()).executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one();
		}
		return findByPrimaryKey(generatedId);
	}


	public Customer update(Customer customer) {
		try(Handle handle = jdbi.open()){
			handle.createUpdate("update customer_db.customer set name=:name,uuid=:uuid,cpf=:cpf,email=:email,birth_date=:birth,gender=:gender,updated_at =:updatedAt where id=:id ")
			.bind("id", customer.getId())
			.bind("name", customer.getName())
			.bind("uuid", customer.getUuid())
			.bind("cpf",customer.getCpf())
			.bind("email", customer.getEmail())
			.bind("birth", customer.getBirthDate())
			.bind("updatedAt", new Date())
			.bind("gender", customer.getGender()).execute();
		}
		return findByPrimaryKey(customer.getId());
	}


	public Customer findByPrimaryKey(Integer id) {
		try(Handle handle = jdbi.open()){
			Optional<Customer> optional = handle.createQuery("SELECT c.id,c.name,uuid,c.cpf,c.email,c.gender,c.birth_date,c.created_at,c.updated_at from customer_db.customer c where c.id = :id ")
					.bind("id", id)
					.map(new CustomerRowMapper()).findOne();

			if(!optional.isPresent()) {
				throw new BusinessException("This customer not exists");
			}
			return optional.get();
		}
	}
	
	
	public Customer findByCpf(String cpf) {
		try(Handle handle = jdbi.open()){
			Optional<Customer> optional = handle.createQuery("SELECT c.id,c.name,uuid,c.cpf,c.email,c.gender,c.birth_date,c.created_at,c.updated_at from customer_db.customer c where c.cpf = :cpf ")
					.bind("cpf",cpf)
					.map(new CustomerRowMapper()).findOne();

			return optional.isPresent() ?  optional.get() : null;
		}
	}


	public List<Customer> findAll(){
		try(Handle handle = jdbi.open()){
			List<Customer> customers = handle.createQuery("SELECT c.id,c.name,uuid,c.cpf,c.email,c.gender,c.birth_date,c.created_at,c.updated_at from customer_db.customer c").map(new CustomerRowMapper()).list();
			return customers;
		}
	}


	public void delete(Integer customerId) {

		try(Handle handle = jdbi.open()){
			handle.createUpdate("delete from customer_db.customer where id = :id")
			.bind("id", customerId).execute();
		}

	}


}
