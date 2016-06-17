package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RepositoryRestResource
public interface AreaRepository extends PagingAndSortingRepository<Area, Long> {
	@Transactional
	List<Area> findAll();
	@Transactional
	List<Area> findByNumber(@Param("number") int number);
	@Transactional
	List<Area> findByType(@Param("type") AreaType type);
	@Transactional
	List<Area> findByAddresses(@Param("address") Address address);
}
