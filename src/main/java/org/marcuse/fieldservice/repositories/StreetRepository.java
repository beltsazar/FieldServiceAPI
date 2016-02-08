package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = StreetProjectionFull.class)
public interface StreetRepository extends PagingAndSortingRepository<Street, Long> {

	List<Street> findByName(@Param("name") String name);

}
