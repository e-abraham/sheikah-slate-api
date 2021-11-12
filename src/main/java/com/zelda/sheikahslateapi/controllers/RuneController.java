package com.zelda.sheikahslateapi.controllers;

import com.zelda.sheikahslateapi.models.Rune;
import com.zelda.sheikahslateapi.services.RuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Manages source of requests
@CrossOrigin("*")
@RestController
//Optional, Every route in controller starts with this
@RequestMapping("/hyrule/runes")
public class RuneController {

    @Autowired
    RuneService runeService;

    @GetMapping
    public List<Rune> getAllRunes() {
        return runeService.getAllRunes();
    }

    @PostMapping
    public Rune createRune(@RequestBody Rune rune) {
        return runeService.saveRune(rune);
    }

    //Curly braces are path variable, like /hyrule/runes/5
    @GetMapping("id/{id}")
    public ResponseEntity<Rune> getRuneById(@PathVariable Integer id) {
        Rune rune = runeService.getRuneById(id);
        return ResponseEntity.ok(rune);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Rune> getRuneByName(@PathVariable String name) {
        Rune rune = runeService.getRuneByName(name);
        return ResponseEntity.ok(rune);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Rune> updateRune(@PathVariable Integer id, @RequestBody Rune runeUpdate) {
        Rune updateRune = runeService.updateRune(id, runeUpdate);
        return ResponseEntity.ok(updateRune);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        runeService.deleteRune(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
