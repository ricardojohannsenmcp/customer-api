package customer.api.models;

import java.util.Collection;
import java.util.Date;

public class Customer {
	
	private Integer id;
	private String uuid;
	private String email;
	private Date birthDate;
	private String cpf;
	private Gender gender;
	private Address mainAddress;
	private Collection<Address> adresses;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Address getMainAddress() {
		return mainAddress;
	}
	public void setMainAddress(Address mainAddress) {
		this.mainAddress = mainAddress;
	}
	public Collection<Address> getAdresses() {
		return adresses;
	}
	public void setAdresses(Collection<Address> adresses) {
		this.adresses = adresses;
	}
	
	
	
	
	
	
}
