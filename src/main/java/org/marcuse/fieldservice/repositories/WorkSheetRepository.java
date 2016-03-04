package org.marcuse.fieldservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WorksheetRepository extends PagingAndSortingRepository<Worksheet, Long> {

	Page<Worksheet> findByAssignment(@Param("assignment") Assignment assignment, Pageable pageable);

}
