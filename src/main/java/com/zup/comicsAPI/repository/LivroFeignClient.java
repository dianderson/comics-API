package com.zup.comicsAPI.repository;

import com.zup.comicsAPI.resource.dto.response.ImportLivroResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        url = "${marvel.url}",
        name = "import-livro")
public interface LivroFeignClient {
    @GetMapping("/{comicId}")
    ImportLivroResponse importLivro(@PathVariable int comicId, @RequestParam(name = "ts") String ts, @RequestParam(name = "apikey") String apikey, @RequestParam(name = "hash") String hash);
}
