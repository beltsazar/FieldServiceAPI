package org.marcuse.fieldservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJpaRepository extends PagingAndSortingRepository<Report, Long> {

    List<Report> findByActive(boolean active);
}