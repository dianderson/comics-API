package com.zup.comicsapi.resource.dto.response;

import com.zup.comicsapi.repository.model.Creator;
import com.zup.comicsapi.repository.model.Comic;
import com.zup.comicsapi.repository.model.Price;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

@Data
public class ComicResponse {
    private Long id;
    private Long comicExternalId;
    private String title;
    private List<Price> prices;
    private List<Creator> creators;
    private String isbn;
    private String description;
    private DayOfWeek dayOfWeek;
    private boolean discountStatus;

    public static ComicResponse of(Comic comic) {
        var response = new ComicResponse();
        response.setId(comic.getId());
        response.setComicExternalId(comic.getExternalComicId());
        response.setTitle(comic.getTitle());
        response.setPrices(comic.getPrice());
        response.setCreators(comic.getCreators());
        response.setIsbn(comic.getIsbn());
        response.setDescription(comic.getDescription());
        return response;
    }
}
