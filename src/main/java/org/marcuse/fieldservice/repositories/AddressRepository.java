package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	List<Address> findByNumber(@Param("number") int number);
	List<Address> findByArea(@Param("area") Area area);

}
