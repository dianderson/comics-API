package com.zup.comicsapi.service;

import com.zup.comicsapi.integration.IntegrationFeignClient;
import com.zup.comicsapi.integration.dto.IntegrationComicResponse;
import com.zup.comicsapi.repository.ComicRepository;
import com.zup.comicsapi.repository.PriceRepository;
import com.zup.comicsapi.repository.model.Creator;
import com.zup.comicsapi.repository.model.Discount;
import com.zup.comicsapi.repository.model.Comic;
import com.zup.comicsapi.repository.model.Price;
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
        return integrationFeignClient.getMarvelComic(comicId, "1", apikey, hash);
    }

    public ComicResponse addToUser(Long comicId, Long userId) {
        var foundComic = comicRepository.findByExternalComicId(comicId);
        if (foundComic.isPresent()) {
            var foundUser = userService.getUserById(userId);
            var comic = foundComic.get();
            comic.addUser(foundUser.getId());
            return ComicResponse.of(comicRepository.save(comic));
        } else {
            var newComic = Comic.of(getMarvelComic(comicId));

            for (Price preco : newComic.getPrice()) {
                priceRepository.save(preco);
            }
            for (Creator creator : newComic.getCreators()) {
                creatorService.save(creator);
            }
            return ComicResponse.of(comicRepository.save(newComic));
        }
    }

    public List<ComicResponse> calculateComicsDiscount(List<ComicResponse> comics) {
        for (ComicResponse comic : comics) {
            String isbn = comic.getIsbn();
            if (!isbn.isBlank()) {
                Integer lastIsbn = Character.getNumericValue(isbn.charAt(isbn.length() - 1));
                Discount discount = discountService.findByFinalIsbn(lastIsbn);
                comic.setDayOfWeek(discount.getDayOfWeek());
                comic.setDiscountStatus(discount.getDayOfWeek() == LocalDate.now().getDayOfWeek());
                if (comic.isDiscountStatus()) {
                    for (Price preco : comic.getPrices()) {
                        preco.setPrice(preco.getPrice() * (1F - discount.getDiscountValue() / 100F));
                    }
                }
            }
        }
        return comics;
    }
}
