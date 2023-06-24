package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.CatalogDTO;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;

    private final SerieServiceClient serieServiceClient;


//
//    @Autowired
//    @Lazy
//    private CatalogService self;

    public CatalogService(SerieServiceClient serieServiceClient, MovieServiceClient movieServiceClient) {
        this.serieServiceClient = serieServiceClient;
        this.movieServiceClient = movieServiceClient;
    }


    //CIRCUIT-BREAKER
    //Agregamos el circuit breaker en esta api ya que se encarga de conectar con la api de series y de movies
    //para armar el catalogo.

    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "catalogcircuit", fallbackMethod = "findMoviesFallBack")
    private List<MovieServiceClient.MovieDto> findAllMoviesByGenre(String genre) {
        var movies = movieServiceClient.getMovieByGenre(genre);
        return movies;
    }
    public List<MovieServiceClient.MovieDto> findMoviesFallBack(String genre, Throwable t) throws Exception {
        throw new Exception("Movie not found");
    }


    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "catalogcircuit", fallbackMethod = "findSeriesFallBack")
    private List<Serie> findAllSeriesByGenre(String genre) {
        var series = serieServiceClient.getSerieByGenre(genre);
        return series;
    }

    public List<Serie> findSeriesFallBack(String genre, Throwable t) throws Exception {
        throw new Exception("Serie Not found");
    }

    public CatalogDTO create(String genre){
        var listaPeliculas = findAllMoviesByGenre(genre);
        var listaSeries = findAllSeriesByGenre(genre);
        return new CatalogDTO(genre, listaPeliculas, listaSeries);
    }






}
