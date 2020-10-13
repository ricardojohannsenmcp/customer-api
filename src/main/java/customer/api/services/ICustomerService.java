package customer.api.services;

import java.util.List;

import customer.api.models.Address;
import customer.api.models.Customer;
import customer.api.models.CustomerFilter;

public interface ICustomerService {
	
	
	public Customer save(Customer customer, Address address);
	
	public Customer update(Customer source,Customer target, Address address);
	
    public List<Customer>  findAll(CustomerFilter filter);
    
    public Customer findByPrimaryKey(Integer customerId);
    
    public void remove(Integer customerId);

	public List<Address> addressesFromCustomer(Integer customerId);

	public Address saveAddress(Customer customer, Address address);
	
	
	public void removeAddressFromCustomer(Customer customer,Address address);
	
	
	public Address updateAddressFromCustomer(Customer customer,Address source, Address target);
	
	
	public Customer findByCpf(String cpf);

}
