package org.marcuse.fieldservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	List<Address> findByNumber(@Param("number") int number);
	Page<Address> findByArea(@Param("area") Area area, Pageable pageable);

}
