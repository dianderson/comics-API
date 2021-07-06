package com.zup.comicsapi.resource;

import com.zup.comicsapi.resource.dto.response.ComicResponse;
import com.zup.comicsapi.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comic")
public class ComicResource {
    private final ComicService comicService;

    @PostMapping("/{id}/{user_id}")
    public ComicResponse addToUser(@PathVariable("id") Long comicId, @PathVariable("user_id") Long userId) {
        return comicService.addToUser(comicId, userId);
    }
}
