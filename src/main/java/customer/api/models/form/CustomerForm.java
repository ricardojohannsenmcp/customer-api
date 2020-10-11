package customer.api.models.form;

import java.util.Date;
import java.util.UUID;

import customer.api.models.Address;
import customer.api.models.Customer;
import customer.api.models.Gender;

public class CustomerForm {
	
	
	private String name;
	private String email;
	private Date birthDate;
	private String cpf;
	private String gender;
	private Address address;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Customer toCustomer() {
		
		Customer customer =  new Customer();
		customer.setName(this.name);
		customer.setBirthDate(this.birthDate);
		customer.setEmail(this.email);
		customer.setCpf(this.cpf);
		customer.setGender(Gender.valueOf(gender));
		customer.setMainAddress(this.address);
		customer.setUuid(UUID.randomUUID().toString());
		return  customer;
		
	}
	
	
	
	
	

}
