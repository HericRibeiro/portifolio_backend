package com.portifolio.prod.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.prod.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    
    boolean existsByMail(String mail);

    Optional<UserModel> findByMail(String mail);
}
