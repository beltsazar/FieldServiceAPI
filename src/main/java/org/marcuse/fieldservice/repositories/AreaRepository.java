package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AreaRepository extends PagingAndSortingRepository<Area, Long> {

	List<Area> findByNumber(@Param("number") int number);

}
