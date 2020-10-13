package customer.api.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException{


	private static final long serialVersionUID = 1L;


	private List<ApiError> errors;


	public BusinessException(String message){
		super(message);

		this.errors =  new ArrayList<ApiError>();
		this.errors.add(new ApiError(message));
	}

	public BusinessException(List<ApiError> errors) {
		super();
		this.errors = errors;
	}

	public List<ApiError> getErrors() {
		return errors;
	}


}
