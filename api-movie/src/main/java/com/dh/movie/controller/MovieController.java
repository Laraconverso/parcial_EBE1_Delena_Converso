package com.dh.movie.controller;

import com.dh.movie.event.MovieCreadaEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {


    private final MovieService movieService;

    private final MovieCreadaEventProducer movieCreadaEventProducer;

    public MovieController(MovieService movieService, MovieCreadaEventProducer movieCreadaEventProducer) {
        this.movieService = movieService;
        this.movieCreadaEventProducer = movieCreadaEventProducer;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }


}
