package com.dh.catalog.model;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.model.serie.Serie;

import java.util.List;

public record CatalogDTO(String genre, List<MovieServiceClient.MovieDto> movies, List<Serie> series) {
}