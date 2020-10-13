package customer.api.exceptions;

import java.util.List;

public class ApiErrorResponse {
	
	
	private String code;
	private List<String> description;
	
	public ApiErrorResponse(String code, List<String> description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	
	public String getCode() {
		return code;
	}
	public List<String> getDescription() {
		return description;
	}
	
	
	
	

}
