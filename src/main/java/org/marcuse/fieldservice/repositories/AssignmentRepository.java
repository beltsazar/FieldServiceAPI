package org.marcuse.fieldservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RepositoryRestResource
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {

	List<Assignment> findByArea(@Param("area") Area area);
	@Transactional
	Page<Assignment> findByCampaign(@Param("campaign") Campaign campaign, Pageable page);
	@Transactional
	Page<Assignment> findByCampaignAndActive(@Param("campaign") Campaign campaign, @Param("active") boolean active, Pageable page);

}
