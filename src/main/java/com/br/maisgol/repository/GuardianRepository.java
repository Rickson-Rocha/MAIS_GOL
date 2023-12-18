package com.br.maisgol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.guardian.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian,Long> {

    Guardian findByCpf(String cpf);
    
}
