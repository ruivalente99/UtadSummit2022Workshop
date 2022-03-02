package com.neoception.workshop.cloud.box;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface
BoxRepository extends CrudRepository<Box, UUID> {
    List<Box> findAll(Pageable pageable);
}
