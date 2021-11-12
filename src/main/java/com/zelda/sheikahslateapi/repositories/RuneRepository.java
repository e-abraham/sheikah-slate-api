package com.zelda.sheikahslateapi.repositories;

import com.zelda.sheikahslateapi.models.Rune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuneRepository extends JpaRepository<Rune, Integer> {
    Rune findByName(String name);
}
