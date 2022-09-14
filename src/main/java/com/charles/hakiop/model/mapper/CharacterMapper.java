package com.charles.hakiop.model.mapper;

import com.charles.hakiop.model.dto.CharacterBasicDTO;
import com.charles.hakiop.model.dto.CharacterDTO;
import com.charles.hakiop.model.entity.CharacterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDTO toDto(CharacterEntity entity);

    CharacterEntity toEntity(CharacterDTO dto);

    CharacterBasicDTO toBasicDto(CharacterEntity entity);

    CharacterEntity toEntity(CharacterBasicDTO dto);
}
