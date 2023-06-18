package com.dh.catalog.service;

import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

        private final MovieRepository movieRepository;

        private final SerieRepository serieRepository;


        public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository) {
            this.movieRepository = movieRepository;
            this.serieRepository = serieRepository;
        }


        public List<Movie> findAllMoviesByGenre(String genre) {
            return movieRepository.findAllByGenre(genre);
        }
        public List<Serie> findAllSeriesByGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
        }


        public Movie save(Movie movie) {
            return movieRepository.save(movie);
        }

        public Serie save(Serie serie) {
        return serieRepository.save(serie);
        }



}
