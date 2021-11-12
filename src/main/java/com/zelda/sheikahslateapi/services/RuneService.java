package com.zelda.sheikahslateapi.services;

import com.zelda.sheikahslateapi.exceptions.ResourceNotFoundException;
import com.zelda.sheikahslateapi.models.Rune;
import com.zelda.sheikahslateapi.repositories.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuneService {

    @Autowired
    RuneRepository runeRepository;

    public List<Rune> getAllRunes() {
        return runeRepository.findAll();
    }

    public Rune getRuneById(Integer id) {
        return runeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rune with id: " + id + " does not exist"));
    }

    public Rune getRuneByName(String name) {
        return runeRepository.findByName(name);
    }

    public Rune saveRune(Rune rune) {
        return runeRepository.save(rune);
    }

    public List<Rune> saveRunes(List<Rune> runes) {
        return runeRepository.saveAll(runes);
    }

    public Rune updateRune(Integer id, Rune runeUpdate) {
        Rune rune = runeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Rune with id: " + id + " does not exist"));
        rune.setName(runeUpdate.getName());
        rune.setDescription(runeUpdate.getDescription());
        rune.setLocation(runeUpdate.getLocation());
        return runeRepository.save(rune);
    }

    public void deleteRune(Integer id) {
        Rune rune = runeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rune with id: " + id + " does not exist"));
        runeRepository.deleteById(id);
    }
}
