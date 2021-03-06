package customer.api.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import customer.api.exceptions.ApiError;
import customer.api.exceptions.BusinessException;
import customer.api.services.CustomerService;

public class Customer {

	private Integer id;
	private String name;
	private String uuid;
	private String email;
	private Date birthDate;
	private String cpf;
	private Gender gender;
	private Date createdAt;
	private Date updatedAt;
	private Address mainAddress;
	private Collection<Address> adresses;

	public void checkValidState(CustomerService customerService) {

		List<ApiError>  errors =  new ArrayList<>();
		if(cpf == null || cpf.trim().isEmpty()) {
			errors.add(new ApiError("the field cpf is mandatory"));
		}
		if(name == null || name.trim().isEmpty()) {
			errors.add(new ApiError("the field name is mandatory"));
		}
		if(email == null || email.trim().isEmpty()) {
			errors.add(new ApiError("the field email is mandatory"));
		}
		if(gender == null) {
			errors.add(new ApiError("the field gender is mandatory"));
		}
		if(birthDate == null) {
			errors.add(new ApiError("the field birth date is mandatory"));
		}

		if(birthDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(birthDate);
			Integer birthYear = cal.get(Calendar.YEAR);

			Calendar now =  Calendar.getInstance();
			Integer nowYear = now.get(Calendar.YEAR);

			if(nowYear - birthYear > 100) {
				errors.add(new ApiError("the customer age cannot be greater be 100"));
			}
			Pattern pattern =  Pattern.compile("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}");
			Matcher matcher =  pattern.matcher(this.cpf);
			if(!matcher.matches()) {
				errors.add(new ApiError("the cpf field should follow the pattern 999.999.999-99"));
			}
			Customer other = customerService.findByCpf(this.cpf);
			if(other != null) {
				if(this.id != null) {
					if(!other.getId().equals(this.getId())) {
						errors.add(new ApiError("the cpf informed is already registered"));
					}
				}else {
					errors.add(new ApiError("the cpf informed is already registered"));
				}
			}
		}
		if(!errors.isEmpty()) {
			throw new BusinessException(errors);
		}
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
