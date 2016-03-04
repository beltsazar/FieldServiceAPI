package org.marcuse.fieldservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {

	Page<Worksheet> findByArea(@Param("area") Area area, Pageable pageable);

}
