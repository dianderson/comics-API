package com.zup.comicsapi.repository.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Creator {
    @Id
    @NotNull
    private Long externalId;
    @NotBlank
    private String resourceURI;
    @NotBlank
    private String name;
    @NotBlank
    private String role;
}
