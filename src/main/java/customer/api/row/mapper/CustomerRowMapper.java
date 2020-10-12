package customer.api.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import customer.api.models.Customer;
import customer.api.models.Gender;

public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		Customer customer =  new Customer();
		customer.setId(rs.getInt("id"));
		customer.setName(rs.getString("name"));
		customer.setUuid(rs.getString("uuid"));
		customer.setEmail(rs.getString("email"));
		customer.setBirthDate(rs.getDate("birth_date"));
		customer.setCreatedAt(rs.getDate("created_at"));
		customer.setUpdatedAt(rs.getDate("updated_at"));
		customer.setCpf(rs.getString("cpf"));
		customer.setGender(Gender.valueOf(rs.getString("gender")));
		return customer;
	}

}
