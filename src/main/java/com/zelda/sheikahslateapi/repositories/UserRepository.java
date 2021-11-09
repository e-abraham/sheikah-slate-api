package com.zelda.sheikahslateapi.repositories;

import com.zelda.sheikahslateapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
