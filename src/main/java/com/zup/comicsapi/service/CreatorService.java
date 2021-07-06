package com.zup.comicsapi.service;

import com.zup.comicsapi.repository.CreatorRepository;
import com.zup.comicsapi.repository.model.Creator;
import com.zup.comicsapi.resource.dto.response.CreatorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final CreatorRepository creatorRepository;

    public CreatorResponse save(Creator creator) {
        String uri = creator.getResourceURI();
        creator.setExternalId(Long.parseLong(uri, uri.lastIndexOf("/") + 1, uri.length(), 10));
        return CreatorResponse.of(creatorRepository.save(creator));
    }
}
