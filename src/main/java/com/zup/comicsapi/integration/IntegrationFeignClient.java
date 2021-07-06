package com.zup.comicsapi.integration;

import com.zup.comicsapi.integration.dto.IntegrationComicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        url = "${marvel.url}",
        name = "import-livro")
public interface IntegrationFeignClient {
    @GetMapping("/{comicId}")
    IntegrationComicResponse getMarvelComic(@PathVariable Long comicId, @RequestParam(name = "ts") String ts, @RequestParam(name = "apikey") String apikey, @RequestParam(name = "hash") String hash);
}
