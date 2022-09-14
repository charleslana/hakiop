package com.charles.hakiop.service;

import com.charles.hakiop.exception.CustomException;
import com.charles.hakiop.model.dto.CharacterBasicDTO;
import com.charles.hakiop.model.dto.CharacterDTO;
import com.charles.hakiop.model.entity.CharacterEntity;
import com.charles.hakiop.model.mapper.CharacterMapper;
import com.charles.hakiop.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CharacterService {

    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Transactional
    public CharacterBasicDTO save(CharacterDTO dto) {
        validateExistsName(dto);
        CharacterEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toBasicDto(entity);
    }

    public CharacterBasicDTO get(Long id) {
        return repository.findById(id).map(mapper::toBasicDto).orElseThrow(() -> new CustomException("character not found."));
    }

    public List<CharacterBasicDTO> getAll() {
        return  repository.findAll().stream().map(mapper::toBasicDto).toList();
    }

    @Transactional
    public CharacterBasicDTO update(CharacterDTO dto) {
        CharacterEntity entity = repository.findById(dto.getId()).orElseThrow(() -> new CustomException("character not found."));
        validateExistsName(dto, entity);
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return mapper.toBasicDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        CharacterBasicDTO dto = get(id);
        repository.delete(mapper.toEntity(dto));
    }

    private void validateExistsName(CharacterDTO dto) {
        boolean existsByName = repository.existsByNameIgnoreCase(dto.getName());
        if (existsByName) {
            throw new CustomException("name already exists.");
        }
    }

    private void validateExistsName(CharacterDTO dto, CharacterEntity entity) {
        boolean existsByName = repository.existsByNameIgnoreCase(dto.getName());
        if (!Objects.equals(entity.getName(), dto.getName()) && existsByName) {
            throw new CustomException("name already exists.");
        }
    }
}
