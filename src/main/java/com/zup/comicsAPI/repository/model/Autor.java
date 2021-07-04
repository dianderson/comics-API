package com.zup.comicsAPI.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Autor {
    @Id
    @NotBlank
    private String resourceURI;
    @NotBlank
    private String name;
    @NotBlank
    private String role;
}
