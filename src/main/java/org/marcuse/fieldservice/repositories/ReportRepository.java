package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {

	//List<Area> findByNumber(@Param("number") int number);

}
