package org.marcuse.fieldservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "address_annotations")
public interface AddressAnnotationRepository extends PagingAndSortingRepository<AddressAnnotation, Long> {

}
