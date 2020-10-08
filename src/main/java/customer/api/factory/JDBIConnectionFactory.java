package customer.api.factory;

import org.jdbi.v3.core.Jdbi;

public class JDBIConnectionFactory {
	
	public static Jdbi getJdbi() {
		Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/customers_db");
		return jdbi;
	}

}
