package com.dh.catalog.model;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.serie.Serie;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "catalog")
public class CatalogDTO {

    private String genre;
    private List<MovieServiceClient.MovieDto> movies;
    private List<Serie> series;

    public CatalogDTO(String genre, List<MovieServiceClient.MovieDto> movies, List<Serie> series) {
        this.genre = genre;
        this.movies = movies;
        this.series = series;
    }

}
