package customer.api.repository;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import com.google.inject.Inject;

import customer.api.models.Address;
import customer.api.row.mapper.AddressRowMapper;

public class AddressRepository implements IAdressRepository{


	@Inject
	private Jdbi jdbi;

	public Address save(Address address) {
		Integer generatedId =  null;
		try(Handle handle = jdbi.open()){
			generatedId = handle.createUpdate("insert into customer_db.address (state,city,neighborhood,zip_code,street,number,additional_information,main,customer_id) values (:state,:city,:neighborhood,:zipCode,:street,:number,:additionalInformation,:main,:customerId) ")
					.bind("state", address.getState())
					.bind("city",address.getCity())
					.bind("customerId",address.getCustomer().getId())
					.bind("neighborhood", address.getNeighborhood())
					.bind("zipCode", address.getZipCode())
					.bind("additionalInformation", address.getAdditionalInformation())
					.bind("street", address.getStreet())
					.bind("number", address.getNumber())
					.bind("main", address.isMain()).executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one();
		}
		return findByPrimaryKey(generatedId);
	}



	public Address update(Address address) {
		try(Handle handle = jdbi.open()){
			handle.createUpdate("update customer_db.address  set state=:state, city =:city, neighborhood =:neighborhood,zip_code =:zipCode,street=:street,number=:number,additional_information=:additionalInformation,main=:main,customer_id=:customerId) where id=:id ")
			.bind("id", address.getId())		
			.bind("state", address.getState())
			.bind("city",address.getCity())
			.bind("customerId",address.getCustomer().getId())
			.bind("neighborhood", address.getNeighborhood())
			.bind("zipCode", address.getZipCode())
			.bind("additionalInformation", address.getAdditionalInformation())
			.bind("street", address.getStreet())
			.bind("number", address.getNumber())
			.bind("main", address.isMain()).execute();
		}
		return findByPrimaryKey(address.getId());
	}


	public Address findByPrimaryKey(Integer id) {
		try(Handle handle = jdbi.open()){
			Optional<Address> optional = handle.createQuery("SELECT a.id,a.state,a.city,a.neighborhood,a.zip_code,a.additional_information,a.street,a.number,a.main,a.customer_id from customer_db.address a where a.id = :id ")
					.bind("id", id)
					.map(new AddressRowMapper()).findOne();
			return optional.isPresent() ? optional.get() : null;
		}
	}




	public List<Address> findByCustomer(Integer customerId) {
		try(Handle handle = jdbi.open()){
			List<Address> adresses= handle.createQuery("SELECT a.id,a.state,a.city,a.neighborhood,a.zip_code,a.additional_information,a.street,a.number,a.main,a.customer_id from customer_db.address a where a.customer_id = :customerId ")
					.bind("customerId", customerId)
					.map(new AddressRowMapper()).list();
			return adresses;
		}
	}


	public void delete(Integer addressId) {

		try(Handle handle = jdbi.open()){
			handle.createUpdate("delete from customer_db.address where id = :id")
			.bind("id", addressId).execute();
		}

	}


}
