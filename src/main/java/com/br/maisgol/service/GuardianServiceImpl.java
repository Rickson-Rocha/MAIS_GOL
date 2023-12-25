package com.br.maisgol.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.maisgol.model.guardian.Guardian;
import com.br.maisgol.repository.GuardianRepository;
import com.br.maisgol.service.exceptions.ObjectNotFoundException;

@Service
public class GuardianServiceImpl implements  GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;
    @Override
    public Guardian findById(Long id) {
        Optional<Guardian> coach = this.guardianRepository.findById(id);
        return coach.orElseThrow(()-> new ObjectNotFoundException("Guardian not found"));
    }

    @Override
    public Page<Guardian> findAllGuardian(Pageable pageable) {
        return guardianRepository.findAll(pageable);
    }

    @Override
    public Guardian createGuardian(Guardian guardian) {

        if (guardian.getCpf() != null) {
            guardian.setCpf(guardian.getCpf().replaceAll("[^0-9]", ""));
        }
       guardian.setId(null);
       Guardian newGuardian = guardianRepository.save(guardian);
       return newGuardian;
    }

    @Override
    public Guardian updateGuardian(Guardian guardian) {
        if (guardian.getCpf() != null) {
            guardian.setCpf(guardian.getCpf().replaceAll("[^0-9]", ""));
        }
        Guardian newGuardian = findById(guardian.getId());
        newGuardian.setName(guardian.getName());
        newGuardian.setCpf(guardian.getCpf());
        newGuardian.setPhone(guardian.getPhone());
        return newGuardian;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Guardian findByCpf(String cpf) {
        return guardianRepository.findByCpf(cpf);
    }
    
}
