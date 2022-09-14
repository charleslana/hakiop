package com.charles.hakiop.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CharacterDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String image;
}
