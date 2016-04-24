package org.marcuse.fieldservice.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

	Account findByUsernameIgnoreCase(@Param("username") String username);

}
