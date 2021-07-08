package com.zup.comicsapi.service;

import com.zup.comicsapi.integration.IntegrationFeignClient;
import com.zup.comicsapi.integration.dto.IntegrationComicResponse;
import com.zup.comicsapi.repository.ComicRepository;
import com.zup.comicsapi.repository.PriceRepository;
import com.zup.comicsapi.repository.model.*;
import com.zup.comicsapi.resource.dto.response.ComicResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComicService {
    private final IntegrationFeignClient integrationFeignClient;
    private final ComicRepository comicRepository;
    private final PriceRepository priceRepository;
    private final CreatorService creatorService;
    private final UserService userService;
    private final DiscountService discountService;

    @Value("${marvel.key}")
    private String apikey;
    @Value("${marvel.hash}")
    private String hash;

    public ComicService(IntegrationFeignClient integrationFeignClient, ComicRepository comicRepository, PriceRepository priceRepository, CreatorService creatorService, @Lazy UserService userService, DiscountService discountService) {
        this.integrationFeignClient = integrationFeignClient;
        this.comicRepository = comicRepository;
        this.priceRepository = priceRepository;
        this.creatorService = creatorService;
        this.userService = userService;
        this.discountService = discountService;
    }

    public IntegrationComicResponse getMarvelComic(Long comicId) {
        var comicResponse = integrationFeignClient.getMarvelComic(comicId, "1", apikey, hash);
        if (comicResponse.getCode() == 200) {
            return comicResponse;
        } else {
            //new EntityNotFoundException(comicResponse.getStatus());
            return null;
        }
    }

    public ComicResponse addToUser(Long comicId, Long userId) {
        var foundComic = comicRepository.findByExternalComicId(comicId);
        var foundUser = userService.getUserById(userId);
        if (foundComic.isPresent()) {
            var comic = foundComic.get();
            comic.addUser(foundUser.getId());
            return ComicResponse.of(comicRepository.save(comic));
        } else {
            var newComic = Comic.of(getMarvelComic(comicId));
            for (Price price : newComic.getPrice()) {
                priceRepository.save(price);
            }
            for (Creator creator : newComic.getCreators()) {
                creatorService.save(creator);
            }
            newComic.addUser(foundUser.getId());
            return ComicResponse.of(comicRepository.save(newComic));
        }
    }

    public List<ComicResponse> calculateComicsDiscount(List<ComicResponse> comics) {
        for (ComicResponse comicResponse : comics) {
            String isbn = comicResponse.getIsbn();
            if (!isbn.isBlank()) {
                Integer lastIsbn = Character.getNumericValue(isbn.charAt(isbn.length() - 1));
                Discount discount = discountService.findByFinalIsbn(lastIsbn);
                comicResponse.setDayOfWeek(discount.getDayOfWeek());
                comicResponse.setDiscountStatus(discount.getDayOfWeek() == LocalDate.now().getDayOfWeek());
                if (comicResponse.isDiscountStatus()) {
                    for (Price price : comicResponse.getPrices()) {
                        price.setPrice(price.getPrice() * (1F - discount.getDiscountValue() / 100F));
                    }
                }
            }
        }
        return comics;
    }
}
