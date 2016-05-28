package org.marcuse.fieldservice.repositories;

import org.hibernate.metamodel.source.binder.Sortable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
