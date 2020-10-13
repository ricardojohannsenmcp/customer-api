package customer.api.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import spark.Request;

public class CustomerFilter {


	private String name;
	private Date birthDate;
	private String state;
	private String city;
	private CustomerFilterSortFields sortField ;
	private FilterOrder order;


	public CustomerFilter(Request request) {

		this.name = request.queryParams("name");
		String birthStr =  request.queryParams("birthDate");

		if(birthStr != null) {
			try {
				this.birthDate = getDateFormat().parse(birthStr);
			} catch (ParseException e) {

			}
		} 

		this.state = request.queryParams("state");
		this.city = request.queryParams("city");

		this.sortField = CustomerFilterSortFields.valueOf("CUSTOMER_NAME");	
		this.order = FilterOrder.valueOf("ASC");


		if(request.queryParams("sortField") != null) {
			try {
				this.sortField = CustomerFilterSortFields.valueOf(request.queryParams("sortField"));
			} catch (Exception e) {

			}
		}
		if(request.queryParams("order") != null) {
			try {
				this.order = FilterOrder.valueOf(request.queryParams("order"));
			} catch (Exception e) {

			}
		}

	}

	public String getName() {
		return name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public String getState() {
		return state;
	}
	public String getCity() {
		return city;
	}
	public CustomerFilterSortFields getSortField() {
		if(this.sortField == null) {
			return CustomerFilterSortFields.CUSTOMER_NAME;
		}
		return sortField;
	}
	public FilterOrder getOrder() {

		if(order == null) {
			return FilterOrder.ASC;
		}
		return order;
	}

	private SimpleDateFormat getDateFormat() {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		return sdf;
	}



}
