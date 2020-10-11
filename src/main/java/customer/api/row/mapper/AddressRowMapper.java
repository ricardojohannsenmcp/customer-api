package customer.api.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import customer.api.models.Address;
import customer.api.models.Customer;

public class AddressRowMapper implements RowMapper<Address>{

	@Override
	public Address map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		Address address =  new Address();
		address.setState(rs.getString("state"));
		address.setCity(rs.getString("city"));
		address.setId(rs.getInt("id"));
		Customer customer = new Customer();
		customer.setId(rs.getInt("customer_id"));
		address.setCustomer(customer);
		address.setNeighborhood(rs.getString("neighborhood"));
		address.setZipCode(rs.getString("zip_code"));
		address.setMain(rs.getBoolean("main"));
		address.setStreet(rs.getString("street"));
		address.setAdditionalInformation(rs.getString("additional_information"));
		address.setNumber(rs.getString("number"));
		return address;
	}

}
