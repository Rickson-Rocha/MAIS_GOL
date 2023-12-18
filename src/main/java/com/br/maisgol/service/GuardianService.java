package com.br.maisgol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.br.maisgol.model.guardian.Guardian;

public interface GuardianService {
    
    Guardian findById(Long id);

    Page<Guardian> findAllGuardian(Pageable pageable);

    Guardian createGuardian(Guardian guardian);

    Guardian updateGuardian(Guardian guardian);

    void delete(Long id);

    Guardian findByCpf(String cpf);
}
