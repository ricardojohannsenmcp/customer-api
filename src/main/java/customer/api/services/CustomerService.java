package customer.api.services;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.google.inject.Inject;

import customer.api.exceptions.BusinessException;
import customer.api.models.Address;
import customer.api.models.Customer;
import customer.api.repository.AddressRepository;
import customer.api.repository.CustomerRepository;

public class CustomerService implements ICustomerService{
	
	@Inject
	private CustomerRepository customerRepository;
	
	@Inject
	private AddressRepository addressRepository;
	
	@Override
	public Customer save(Customer customer, Address address) {
		
		Customer createdCustomer = customerRepository.save(customer);
	       address.setCustomer(createdCustomer);
	       address.setMain(true);
	       addressRepository.save(address);
	       carregarEnderecos(createdCustomer);
		return createdCustomer;
	}



	private void carregarEnderecos(Customer customer) {
		List<Address> addresses = addressRepository.findByCustomer(customer.getId());
			 customer.setAdresses(addresses);
			for(Address address : addresses) {
				if(address.isMain()) {
					customer.setMainAddress(address);
					break;
				}
			}
	}
	
	
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}


	@Override
	public Customer findByPrimaryKey(Integer id) {
		Customer customer = customerRepository.findByPrimaryKey(id);
		if(customer != null) {
		       carregarEnderecos(customer);
		}
		return customer;
	}


	@Override
	public void remove(Integer customerId) {
		List<Address> addresses =  addressRepository.findByCustomer(customerId);
		if(!addresses.isEmpty()) {
			addresses.forEach(a ->{
				addressRepository.delete(a.getId());
			});	
		}
		customerRepository.delete(customerId);
	}



	@Override
	public List<Address> addressesFromCustomer(Integer customerId) {
		return addressRepository.findByCustomer(customerId);
	}



	@Override
	public Address saveAddress(Customer customer, Address address) {
		address.setCustomer(customer);
		return addressRepository.save(address);
	}



	@Override
	public void removeAddressFromCustomer(Integer customerId,Integer addressId) {
		
		Customer customer = findByPrimaryKey(customerId);
		
		if(customer == null) {
			throw new BusinessException("This Customer not exists");
		}
		Address address =  addressRepository.findByPrimaryKey(addressId);
		if(address == null) {
			throw new BusinessException("This Address not exists");
		}
		if(!address.getCustomer().getId().equals(customer.getId())) {
			throw new BusinessException("This Address not belong to this customer");
		}
		if(address.isMain()) {
			throw new BusinessException("Cannot remove. The customer needs own one main address");
		}
		addressRepository.delete(address.getId());
	}



	@Override
	public Address updateAddressFromCustomer(Customer customer, Address source, Address target) {
		
		if(!target.getCustomer().getId().equals(customer.getId())) {
			throw new BusinessException("This address not belong to this customer");
		}
		target.setState(source.getState());
		target.setCity(source.getCity());
		target.setNumber(source.getNumber());
		target.setAdditionalInformation(source.getAdditionalInformation());
		target.setNeighborhood(source.getNeighborhood());
		target.setMain(source.isMain());
		target.setStreet(source.getStreet());
		target.setZipCode(source.getZipCode());
		Address updated = addressRepository.update(target);
		return updated;
	}



	
	
	

}
