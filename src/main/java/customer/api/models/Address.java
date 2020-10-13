package customer.api.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import customer.api.exceptions.ApiError;
import customer.api.exceptions.BusinessException;

public class Address {


	private Integer id;
	private String state;
	private String city;
	private String neighborhood;
	private String zipCode;
	private String street;
	private String number;
	private String additionalInformation;
	private boolean main;

	@JsonIgnore
	private Customer customer;



	public void checkbelongsTo(Customer other) {
		if(!this.customer.getId().equals(other.getId())) {
			throw new BusinessException("This Address not belong to this customer");
		}
	}

	public void checkCanBeDeleted() {
		if(this.main) {
			throw new BusinessException("Cannot remove. The customer needs own one main address");
		}
	}

	public void checkValidState() {

		List<ApiError> errors =  new ArrayList<>();
		if(city == null || city.trim().isEmpty()) {
			errors.add(new ApiError("field city is mandatory"));
		}
		if(state == null || state.trim().isEmpty()) {
			errors.add(new ApiError("field state is mandatory"));
		}
		if(zipCode == null || zipCode.trim().isEmpty()) {
			errors.add(new ApiError("field zip code is mandatory"));
		}
		if(neighborhood == null  || neighborhood.trim().isEmpty()) {
			errors.add(new ApiError("field neighborhod is mandatory"));
		}
		if(!errors.isEmpty()) {
			throw new BusinessException(errors);
		}
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public boolean isMain() {
		return main;
	}
	public void setMain(boolean main) {
		this.main = main;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}





}
