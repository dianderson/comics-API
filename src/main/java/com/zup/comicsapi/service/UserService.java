package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.UserRepository;
import com.zup.comicsapi.repository.model.User;
import com.zup.comicsapi.resource.dto.response.ComicResponse;
import com.zup.comicsapi.resource.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ComicService comicService;

    public UserResponse save(User user) {
        return UserResponse.of(userRepository.save(user));
    }

    public UserResponse findById(Long userId) {
        var foundUser = getUserById(userId);
        List<ComicResponse> returnComics = comicService.calculateComicsDiscount(foundUser.getComics());
        foundUser.setComics(returnComics);
        return foundUser;
    }

    public UserResponse getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(UserResponse::of)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", id)));
    }
}
