package org.marcuse.fieldservice.repositories;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RepositoryRestResource
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {

	List<Assignment> findByArea(@Param("area") Area area);

	@Transactional
	Page<Assignment> findByCampaign(@Param(value="campaign") Campaign campaign, Pageable page);

	@Transactional
	Page<Assignment> findByCampaignAndActive(@Param("campaign") Campaign campaign, @Param("active") boolean active, Pageable page);

	@Transactional
	Page<Assignment> findByActive(@Param("active") boolean active, Pageable page);

	@Transactional
	Page<Assignment> findByCreationDateGreaterThanEqual(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationDate, Pageable page);

	@Transactional
	List<Assignment> findByAreaAndCampaign(@Param("area") Area area, @Param("campaign") Campaign campaign);

	@Transactional
	List<Assignment> findByAreaAndCreationDateGreaterThanEqual(@Param("area") Area area, @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime creationDate);

}
