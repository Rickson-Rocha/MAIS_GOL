package com.br.maisgol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.maisgol.model.user.User;

public interface UserRepository  extends JpaRepository<User,Long>{

    User findByName(String name);
    
}
