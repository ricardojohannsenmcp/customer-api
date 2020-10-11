package customer.api.services;

import java.util.List;

import customer.api.models.Address;
import customer.api.models.Customer;

public interface ICustomerService {
	
	
	public Customer save(Customer customer, Address address);
	
    public List<Customer>  findAll();
    
    public Customer findByPrimaryKey(Integer customerId);
    
    public void remove(Integer customerId);

	public List<Address> addressesFromCustomer(Integer customerId);

	public Address saveAddress(Customer customer, Address address);
	
	
	public void removeAddressFromCustomer(Integer customerId,Integer addressId);
	
	
	public Address updateAddressFromCustomer(Customer customer,Address source, Address target);

}