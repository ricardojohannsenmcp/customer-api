package customer.api.repository;

import java.util.List;

import customer.api.models.Customer;

public interface ICustomerRepository {
	
	public Customer save(Customer customer);
	
	public Customer findByPrimaryKey(Integer id);
	
	public List<Customer> findAll();
	
	public void delete(Integer customerId);
	
	public Customer update(Customer customer);
	
	

}
