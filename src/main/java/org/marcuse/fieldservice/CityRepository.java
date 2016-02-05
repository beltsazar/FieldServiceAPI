package org.marcuse.fieldservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource()
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

	List<City> findByName(@Param("name") String name);

}
