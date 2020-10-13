package customer.api.models;

public enum CustomerFilterSortFields {
	
	CUSTOMER_NAME("name"), 
	CUSTOMER_BIRTH_DATE("birth_date"),
	CUSTOMER_CREATED_AT("created_at"),
	ADDRESS_STATE("state"),
	ADDRESS_CITY("city");

    private CustomerFilterSortFields(String campo) {
		this.campo = campo;
	}

	private String campo;

	public String getCampo() {
		return campo;
	}
	
	 public static CustomerFilterSortFields fromString(String text) {
	        for (CustomerFilterSortFields b : CustomerFilterSortFields.values()) {
	            if (b.campo.equalsIgnoreCase(text)) {
	                return b;
	            }
	        }
	        return null;
	    }

}
