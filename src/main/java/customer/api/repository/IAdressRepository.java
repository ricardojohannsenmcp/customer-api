package customer.api.repository;

import java.util.List;

import customer.api.models.Address;

public interface IAdressRepository {
	
	public Address save(Address address);
	
	public Address findByPrimaryKey(Integer id);
	
	public List<Address> findByCustomer(Integer customerId);
	
	public void delete(Integer addressId);
	
	

}
