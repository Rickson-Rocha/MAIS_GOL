package com.br.maisgol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.maisgol.model.enums.Status;
import com.br.maisgol.model.group.Group;

public interface GroupService {
    Group findById(Long id);

    Page<Group> findAllGroup(Pageable pageable);

    Group createGroup(Group group);


    Group updateGroup(Group group);

    void delete(Long id);

    Page<Group> findActiveGroups(Pageable pageable);

    Page<Group> findInactiveGroups(Pageable pageable);

    Page<Group> findVacationGuardins(Pageable pageable);

    Page<Group> findGroupsByCriteria(String name, Status status,Pageable pageable);

    boolean doesGroupExistByName(String groupName);
}
