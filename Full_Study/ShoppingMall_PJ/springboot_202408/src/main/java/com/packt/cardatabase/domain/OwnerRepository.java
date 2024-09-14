package com.packt.cardatabase.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Optional<Owner> findByFirstname(String firstName);

	// lastname으로 검색
	Optional<Owner> findByLastname(String lastname);

	// firstname과 lastname 모두로 검색 (AND 조건)
	Optional<Owner> findByFirstnameAndLastname(String firstname, String lastname);
}
