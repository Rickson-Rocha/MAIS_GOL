package com.br.maisgol.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.enums.Status;
import com.br.maisgol.model.group.Group;

public interface GroupRepository extends JpaRepository<Group,Long>{

    Page<Group> findByStatus(Status active, Pageable pageable);

    Page<Group> findByNameAndStatus(String name, Status status, Pageable pageable);

    boolean existsByName(String groupName);

    Group findByName(String groupName);

    Optional<Group> findById(Long id);

    Group findGroupByName(String groupName);
    
}
