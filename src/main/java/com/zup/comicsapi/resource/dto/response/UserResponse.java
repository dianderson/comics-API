package com.zup.comicsapi.resource.dto.response;

import com.zup.comicsapi.repository.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private List<ComicResponse> comics;

    public static UserResponse of(User user) {
        var response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCpf(user.getCpf());
        response.setBirthDate(user.getBirthDate());
        response.setComics(ComicResponse.of(user.getComics()));
        return response;
    }

    public static UserResponse of(User user, List<ComicResponse> comics) {
        var response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCpf(user.getCpf());
        response.setBirthDate(user.getBirthDate());
        response.setComics(comics);
        return response;
    }
}
