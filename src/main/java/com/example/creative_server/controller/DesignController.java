package com.example.creative_server.controller;

import com.example.creative_server.creativeapp.DesignDto;
import com.example.creative_server.creativeapp.Design;
import com.example.creative_server.repository.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/designs")
public class DesignController {

    @Autowired
    private DesignRepository designRepository;

    @GetMapping
    public List<Design> getAllDesigns() {
        return designRepository.findAll();
    }

    @PostMapping
    public Design createDesign(@RequestBody Design design) {
        return designRepository.save(design);
    }

    @GetMapping("/by-url")
    public ResponseEntity<Design> getByImageUrl(@RequestParam String url) {
        return designRepository.findByImageUrl(url)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/today")
    public ResponseEntity<DesignDto> getTodayDesign() {
        Optional<Design> design = designRepository.findByDate(LocalDate.now());

        return design.map(d -> ResponseEntity.ok(new DesignDto(
                        d.getImageUrl(),
                        d.getFact(),
                        d.getDate(),
                        d.getAuthor() // добавляем автора
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
