package customer.api.repository;

import java.util.List;

import customer.api.models.Customer;
import customer.api.models.CustomerFilter;

public interface ICustomerRepository {
	
	public Customer save(Customer customer);
	
	public Customer findByPrimaryKey(Integer id);
	
	public List<Customer> findAll(CustomerFilter filter);
	
	public void delete(Integer customerId);
	
	public Customer update(Customer customer);
	
	

}
