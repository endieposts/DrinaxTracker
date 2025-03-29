package com.endie.drinaxtracker.controller;

import com.endie.drinaxtracker.model.StarSystem;
import com.endie.drinaxtracker.repository.StarSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.endie.drinaxtracker.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class StarSystemController {

    @Autowired
    StarSystemRepository starSystemRepository;

    @GetMapping("/starsystems")
    public List<StarSystem> getAllStarSystems() {
        return starSystemRepository.findAll();
    }

    @GetMapping("/starsystems/{id}")
    public StarSystem getStarSystemById(@PathVariable Long id) {
        return starSystemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("StarSystem not found with id " + id));
    }

    @PostMapping("/starsystems")
    public StarSystem createStarSystem(@Valid @RequestBody StarSystem starSystem) {
        return starSystemRepository.save(starSystem);
    }

    @PutMapping("/starsystems/{id}")
    public StarSystem updateStarSystem(@PathVariable Long id, @Valid @RequestBody StarSystem starSystemRequest) {
        return starSystemRepository.findById(id).map(starSystem -> {
            starSystem.setName(starSystemRequest.getName());
            starSystem.setNotes(starSystemRequest.getNotes());
            starSystem.setSector(starSystemRequest.getSector());
            starSystem.setSubSector(starSystemRequest.getSubSector());
            starSystem.setDrinaxAttitude(starSystemRequest.getDrinaxAttitude());
            starSystem.setPartyAttitude(starSystemRequest.getPartyAttitude());

            return starSystemRepository.save(starSystem);
        }).orElseThrow(() -> new ResourceNotFoundException("StarSystem not found with id " + id));
    }

    @DeleteMapping("/starsystems/{id}")
    public ResponseEntity<?> deleteStarSystem(@PathVariable Long id) {
        return starSystemRepository.findById(id).map(starSystem -> {
            starSystemRepository.delete(starSystem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("StarSystem not found with id " + id));
    }

    @GetMapping("/showstarsystems")
    public String showShowStarSystemsPage(Model model) {
        model.addAttribute("starSystems", starSystemRepository.findAll());
        return "showstarSystems";
    }

    @PostMapping("/addstarsystem")
    public String addStarSystem(@Valid StarSystem starSystem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-starSystem";
        }

        starSystemRepository.save(starSystem);
        return "redirect:/showstarSystems";
    }

    @GetMapping("/editstarsystem/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        StarSystem starSystem = starSystemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid starSystem Id:" + id));

        model.addAttribute("starSystem", starSystem);
        return "update-starSystem";
    }

    @PostMapping("/updatestarsystem/{id}")
    public String updateStarSystem(@PathVariable("id") long id, @Valid StarSystem starSystem,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            starSystem.setId(id);
            return "update-starSystem";
        }

        starSystemRepository.save(starSystem);
        return "redirect:/showstarSystems";
    }

    @GetMapping("/deletestarsystem/{id}")
    public String deleteStarSystem(@PathVariable("id") long id, Model model) {
        StarSystem starSystem = starSystemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid starSystem Id:" + id));
        starSystemRepository.delete(starSystem);
        return "redirect:/showstarSystems";
    }

}
