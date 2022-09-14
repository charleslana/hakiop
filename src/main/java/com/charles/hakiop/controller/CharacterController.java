package com.charles.hakiop.controller;

import com.charles.hakiop.model.dto.CharacterBasicDTO;
import com.charles.hakiop.model.dto.CharacterDTO;
import com.charles.hakiop.service.CharacterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
@Slf4j
public class CharacterController {

    private final CharacterService service;

    @PostMapping
    public ResponseEntity<CharacterBasicDTO> save(@RequestBody @Valid CharacterDTO dto) {
        log.info("REST request to create character: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterBasicDTO> get(@PathVariable("id") Long id) {
        log.info("REST request to get character: {}", id);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getAll() {
        log.info("REST request to get all characters");
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping
    public ResponseEntity<CharacterBasicDTO> update(@RequestBody @Valid CharacterDTO dto) {
        log.info("REST request to update character: {}", dto);
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("REST request to delete character: {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
