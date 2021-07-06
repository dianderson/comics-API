package com.zup.comicsapi.repository.model;

import com.zup.comicsapi.resource.dto.request.CreateUserRequest;
import com.zup.comicsapi.resource.dto.response.UserResponse;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Column(unique = true)
    private String cpf;
    @NotNull
    private LocalDate birthDate;
    @ManyToMany
    private List<Comic> comics;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public static User of(CreateUserRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setCpf(request.getCpf());
        user.setBirthDate(request.getBirthDate());
        user.setComics(request.getComics());
        return user;
    }

    public static User of(UserResponse request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setCpf(request.getCpf());
        user.setBirthDate(request.getBirthDate());
        //TODO usuario.setComics(request.getComics());
        return user;
    }
}
