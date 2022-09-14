package com.charles.hakiop.repository;

import com.charles.hakiop.model.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    @Query("select (count(c) > 0) from CharacterEntity c where upper(c.name) = upper(?1)")
    Boolean existsByNameIgnoreCase(String name);
}
