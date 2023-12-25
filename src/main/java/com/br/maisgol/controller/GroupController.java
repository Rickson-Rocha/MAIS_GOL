package com.br.maisgol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.service.GroupService;
import com.br.maisgol.service.exceptions.ConflictException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("group")
@Validated
public class GroupController {

    @Autowired
    private GroupService groupService;

   @GetMapping("/{id}")
   public ResponseEntity<Group> findById(@PathVariable Long id){
    Group group = this.groupService.findById(id);
    return ResponseEntity.ok(group);
   }

   @GetMapping
    public ResponseEntity<Page<Group>> findAllGroup(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Group> groupPage = groupService.findAllGroup(pageable);
        return ResponseEntity.ok(groupPage);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody Group group, UriComponentsBuilder uriBuilder){
        Group createdGroup = groupService.createGroup(group);
        var uri = uriBuilder.path("/groups/{id}").buildAndExpand(createdGroup.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Group> update(@PathVariable Long id,@RequestBody @Valid Group group) {
        group.setId(id);
        Group localGroup = groupService.updateGroup(group);

        return ResponseEntity.ok(localGroup);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ConflictException {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<Page<Group>> getActiveGroup(
            @PageableDefault(size = 10) Pageable pageable 
    ) {
        Page<Group> activeGroupPage = groupService.findActiveGroups(pageable);
        return ResponseEntity.ok(activeGroupPage);
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<Group>> getInactiveGroup(
            @PageableDefault(size = 10) Pageable pageable 
    ) {
        Page<Group> inactiveGroupPage = groupService.findActiveGroups(pageable);
        return ResponseEntity.ok(inactiveGroupPage);
    }

    @GetMapping("/vacation")
    public ResponseEntity<Page<Group>> getVactionGroup(
            @PageableDefault(size = 10) Pageable pageable 
) {
        Page<Group> vacationGroupPage = groupService.findActiveGroups(pageable);
        return ResponseEntity.ok(vacationGroupPage);
    }


    
}
