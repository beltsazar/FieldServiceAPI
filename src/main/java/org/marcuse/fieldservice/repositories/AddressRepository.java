package org.marcuse.fieldservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	List<Address> findByNumber(@Param("number") int number);
	List<Address> findByArea(@Param("area") Area area, Sort sort);
	List<Address> findByAreaOrderByStreetName(@Param("area") Area area);

}
