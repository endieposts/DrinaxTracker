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

    @GetMapping("/starSystems")
    public List<StarSystem> getAllStarSystems() {
        return starSystemRepository.findAll();
    }
}
