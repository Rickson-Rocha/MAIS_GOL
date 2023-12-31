package com.br.maisgol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.maisgol.model.enums.Status;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.repository.GroupRepository;
import com.br.maisgol.service.exceptions.ConflictException;
import com.br.maisgol.service.exceptions.ObjectNotFoundException;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;


    @Override
    public Group findById(Long id) {
       Optional<Group> group = this.groupRepository.findById(id);
        return group.orElseThrow(()-> new ObjectNotFoundException("Coach not found"));
    }

    @Override
    public Page<Group> findAllGroup(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Group createGroup(Group group) {

        group.setId(null); // Certifique-se de que o ID seja nulo para criar um novo grupo

        Group newGroup = groupRepository.save(group);
        return newGroup;
    }

    @Override
    public Group updateGroup(Group group) {
        Group newGroup = findById(group.getId());
        newGroup.setName(group.getName());
        newGroup.setStatus(group.getStatus());
        return newGroup;
    }

    @Override
    public void delete(Long id)throws ConflictException {
        Group group = findById(id);
    
        if (group.getStatus() == Status.INACTIVE) {
            throw new ConflictException("Group is already inactive.");
        }
        
        group.setStatus(Status.INACTIVE);
        updateGroup(group);
    }

    @Override
    public Page<Group> findActiveGroups(Pageable pageable) {
        return groupRepository.findByStatus(Status.ACTIVE,pageable);
    }

    @Override
    public Page<Group> findInactiveGroups(Pageable pageable) {
        return groupRepository.findByStatus(Status.INACTIVE,pageable);
    }

    @Override
    public Page<Group> findVacationGroups(Pageable pageable) {
        return groupRepository.findByStatus(Status.VACATION,  pageable);
    }

    @Override
    public Page<Group> findGroupsByCriteria(String name, Status status, Pageable pageable) {
        return groupRepository.findByNameAndStatus(name, status,pageable);
    }

    @Override
    public boolean doesGroupExistByName(String groupName) {
        return groupRepository.existsByName(groupName);
    }

   
    
}
