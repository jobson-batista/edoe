package com.edoe.api.repositories;

import com.edoe.api.models.Descriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptorRepository extends JpaRepository<Descriptor, Long> {

}
