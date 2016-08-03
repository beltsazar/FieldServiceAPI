package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RepositoryRestResource
public interface WorksheetRepository extends PagingAndSortingRepository<Worksheet, Long> {
	@Transactional
	List<Worksheet> findByShared(@Param("visible") boolean shared);
}
