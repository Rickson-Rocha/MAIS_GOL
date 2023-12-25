package com.br.maisgol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.maisgol.model.athletes.Athletes;
import com.br.maisgol.model.enums.Status;
import com.br.maisgol.model.group.Group;
import com.br.maisgol.model.guardian.Guardian;
import com.br.maisgol.repository.AthletesRepository;
import com.br.maisgol.service.exceptions.ObjectNotFoundException;

@Service
public class AthletesServiceImpl implements AthletesService {
    @Autowired
    private AthletesRepository athletesRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GuardianService guardianService;

    @Override
    public Athletes findById(Long id) {
        Optional<Athletes> athlete = this.athletesRepository.findById(id);
        return athlete.orElseThrow(()-> new ObjectNotFoundException("Athlete not found"));
    }

    @Override
    public Page<Athletes> findAthletes(Pageable pageable) {
       return athletesRepository.findAll(pageable);
    }

    @Override
    public Athletes createAthlete(Athletes athlete) {
        athlete.setId(null);
    
        // Buscar o guardião (guardian) pelo CPF
        Guardian guardian = guardianService.findByCpf(athlete.getGuardian().getCpf());
        if (guardian == null) {
            throw new ObjectNotFoundException("Guardian not found for CPF: " + athlete.getGuardian().getCpf());
        }
    
        // Verificar se o grupo com o ID fornecido existe
        Group group = groupService.findById(athlete.getGroup().getId());
        if (group == null) {
            throw new ObjectNotFoundException("Group not found with ID: " + athlete.getGroup().getId());
        }
    
        // Associar o guardião e o grupo ao atleta
        athlete.setGuardian(guardian);
        athlete.setGroup(group);
    
        // Salvar o novo atleta
        return athletesRepository.save(athlete);
    }
    

    @Override
    public Athletes updateAthletes(Athletes athletes) {
       Athletes newAthelete = findById(athletes.getId());

        String groupName = athletes.getGroup().getName(); // pegou nome do grupo
        boolean doesGroupExist = groupService.doesGroupExistByName(groupName); // verifca se o nome do grupo existe
        
        if (!doesGroupExist) {
            throw new IllegalArgumentException("Grou´p not found ! .");
        }
       newAthelete.setCpf(athletes.getCpf());
       newAthelete.setStatus(athletes.getStatus());
       newAthelete.setHeight(athletes.getHeight());
       newAthelete.setWeight(athletes.getWeight());
       newAthelete.setGroup(athletes.getGroup());

       return newAthelete;
    }

    @Override
    public void delete(Long id) {
     Athletes athletes = findById(id);
    
        if (athletes.getStatus() == Status.INACTIVE) {
            throw new RuntimeException("Athlete is already inactive.");
        }
        
        athletes.setStatus(Status.INACTIVE);
        updateAthletes(athletes);
    }
    

    @Override
    public Page<Athletes> findActiveAthletes(Pageable pageable) {
        return athletesRepository.findByStatus(Status.ACTIVE,pageable);
    }

    @Override
    public Page<Athletes> findInactiveAthletes(Pageable pageable) {
        return athletesRepository.findByStatus(Status.INACTIVE,pageable);
    }

    @Override
    public Page<Athletes> findAthletesByCriteria(String name, Status status, Pageable pageable) {
        return athletesRepository.findByNameAndStatus(name, status,pageable);
    }
    
}
