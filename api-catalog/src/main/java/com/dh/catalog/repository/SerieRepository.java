package com.dh.catalog.repository;

import com.dh.catalog.event.SerieCreadaEventConsumer;
import com.dh.catalog.model.serie.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Serie, String> {

    static void save(SerieCreadaEventConsumer.Data message) {
    }

    @Query("{'genre': ?0}")
    List<Serie> findAllByGenre(String genre);

}
