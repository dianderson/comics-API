package com.zup.comicsapi.resource.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.comicsapi.repository.model.Comic;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateUserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String cpf;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private List<Comic> comics = new ArrayList<>();
}
