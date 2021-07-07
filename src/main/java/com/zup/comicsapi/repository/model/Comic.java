package com.zup.comicsapi.repository.model;

import com.zup.comicsapi.integration.dto.IntegrationComicResponse;
import com.zup.comicsapi.integration.dto.ExternalResult;
import com.zup.comicsapi.resource.dto.response.ComicResponse;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long externalComicId;
    @NotBlank
    private String title;
    @OneToMany
    private List<Price> price;
    @NotNull
    @ManyToMany
    private List<Creator> creators;
    private String isbn;
    @NotBlank
    @Column(length = 1500)
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_comic",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "comic_id")})
    private List<User> users;

    public static Comic of(IntegrationComicResponse response) {
        var livro = new Comic();
        for (ExternalResult externalResult : response.getExternalData().getExternalResults()) {
            livro.setExternalComicId(externalResult.getId());
            livro.setTitle(externalResult.getTitle());
            livro.setIsbn(externalResult.getIsbn());
            livro.setDescription(externalResult.getDescription());
            livro.setPrice(externalResult.getPrices());
            livro.setCreators(externalResult.getExternalCreators().getCreators());
        }
        return livro;
    }

    public static Comic of(ComicResponse response) {
        var livro = new Comic();
        livro.setId(response.getId());
        livro.setExternalComicId(response.getComicExternalId());
        livro.setTitle(response.getTitle());
        livro.setIsbn(response.getIsbn());
        livro.setDescription(response.getDescription());
        livro.setPrice(response.getPrices());
        livro.setCreators(response.getCreators());
        return livro;
    }

    public void addUser(Long userId) {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        this.users.add(new User(userId));
    }
}
