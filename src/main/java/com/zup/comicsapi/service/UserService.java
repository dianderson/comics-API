package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.UserRepository;
import com.zup.comicsapi.repository.model.User;
import com.zup.comicsapi.resource.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        foundUser.setComics(comicService.calculateComicsDiscount(foundUser.getComics()));
        return foundUser;
    }

    public UserResponse getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(UserResponse::of)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s not found", id)));
    }
}
