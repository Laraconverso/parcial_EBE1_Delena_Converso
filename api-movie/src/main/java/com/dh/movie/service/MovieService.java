package com.dh.movie.service;


import com.dh.movie.event.MovieCreadaEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieService {


    private final MovieRepository movieRepository;

    private final MovieCreadaEventProducer movieCreadaEventProducer;

    public MovieService(MovieRepository movieRepository, MovieCreadaEventProducer movieCreadaEventProducer) {
        this.movieRepository = movieRepository;
        this.movieCreadaEventProducer = movieCreadaEventProducer;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }


    public Movie save(Movie movie) {
        movieRepository.save(movie);
        movieCreadaEventProducer.publishMovieCreada(new MovieCreadaEventProducer.Data(movie.getId(),movie.getName(), movie.getGenre()));
        System.out.println("Guardando pelicula: " + movie.getName());
        return movie;
    }


}
