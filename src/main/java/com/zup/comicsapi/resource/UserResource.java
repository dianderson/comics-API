package com.zup.comicsapi.resource;

import com.zup.comicsapi.DataLoader;
import com.zup.comicsapi.repository.model.User;
import com.zup.comicsapi.resource.dto.request.CreateUserRequest;
import com.zup.comicsapi.resource.dto.response.UserResponse;
import com.zup.comicsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserResource {
    private final UserService userService;
    private final DataLoader dataLoader;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody @Valid CreateUserRequest request) {
        return userService.save(User.of(request));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/descontos")
    public void discount() {
        dataLoader.loadData();
    }
}
