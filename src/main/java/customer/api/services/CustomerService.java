package customer.api.services;

import java.util.List;

import com.google.inject.Inject;

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
		customer.checkValidState(this);
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
	public void removeAddressFromCustomer(Customer customer, Address address) {
		address.checkbelongsTo(customer);
		address.checkCanBeDeleted();
		addressRepository.delete(address.getId());
	}



	@Override
	public Address updateAddressFromCustomer(Customer customer, Address source, Address target) {
		target.checkbelongsTo(customer);
		target.setState(source.getState());
		target.setCity(source.getCity());
		target.setNumber(source.getNumber());
		target.setAdditionalInformation(source.getAdditionalInformation());
		target.setNeighborhood(source.getNeighborhood());
		target.setMain(source.isMain());
		target.setStreet(source.getStreet());
		target.setZipCode(source.getZipCode());
		Address updated = addressRepository.update(target);
		if(updated.isMain()) {
			List<Address> addresses = addressesFromCustomer(customer.getId());
			addresses.forEach(a ->{
				if(!updated.getId().equals(a.getId())) {
					addressRepository.resetMainAddress(a);
				}
			});
		}
		return updated;
	}



	@Override
	public Customer findByCpf(String cpf) {
		return customerRepository.findByCpf(cpf);
	}



	@Override
	public Customer update(Customer source,Customer target, Address address) {
		
		target.setName(source.getName());
		target.setCpf(source.getCpf());
		target.setGender(source.getGender());
		target.setBirthDate(source.getBirthDate());
		target.setEmail(source.getEmail());
		target.checkValidState(this);
		Customer updatedCustomer = customerRepository.update(target);
		if(address != null) {
			Address originalAddress = addressRepository.findMainAddressByCustomer(updatedCustomer.getId());
	       originalAddress.setCustomer(updatedCustomer);
	       originalAddress.setStreet(address.getStreet());
	       originalAddress.setCity(address.getCity());
	       originalAddress.setState(address.getState());
	       originalAddress.setZipCode(address.getZipCode());
	       originalAddress.setNeighborhood(address.getNeighborhood());
	       originalAddress.setNumber(address.getNumber());
	       originalAddress.setId(originalAddress.getId());
	       originalAddress.setAdditionalInformation(address.getAdditionalInformation());
	       originalAddress.setMain(true);
	       addressRepository.update(originalAddress);
		}
	       carregarEnderecos(updatedCustomer);
		return updatedCustomer;
	}



	
	
	

}
