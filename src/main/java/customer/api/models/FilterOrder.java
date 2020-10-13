package customer.api.models;

public enum FilterOrder {
	
	ASC("asc"),DESC("desc");

	private FilterOrder(String order) {
		this.order = order;
	}

	private String order;

	public String getOrder() {
		return order;
	}
	
	 public static FilterOrder fromString(String text) {
	        for (FilterOrder o : FilterOrder.values()) {
	            if (o.order.equalsIgnoreCase(text)) {
	                return o;
	            }
	        }
	        return null;
	    }
	

}
