package com.dh.catalog.repository;

import com.dh.catalog.event.MovieCreadaEventConsumer;
import com.dh.catalog.event.SerieCreadaEventConsumer;
import com.dh.catalog.model.CatalogDTO;
import com.dh.catalog.model.movie.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface CatalogRepository extends MongoRepository<CatalogDTO, String> {
    Optional<CatalogDTO> findByGenre(String genre);

    static void saveMsjMovie(MovieCreadaEventConsumer.Data message) {
    }

    static void saveMsjSerie(SerieCreadaEventConsumer.Data message) {
    }



}