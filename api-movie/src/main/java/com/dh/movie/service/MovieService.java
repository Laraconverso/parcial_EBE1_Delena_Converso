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
        return movieRepository.save(movie);
    }

    @Transactional
    public String create(Movie entity) throws Exception {
        movieRepository.save(entity);
        movieCreadaEventProducer.publishMovieCreada(new MovieCreadaEventProducer.Data(entity.getId(),entity.getName(), entity.getGenre()));
        return entity.getId().toString();
    }

}
