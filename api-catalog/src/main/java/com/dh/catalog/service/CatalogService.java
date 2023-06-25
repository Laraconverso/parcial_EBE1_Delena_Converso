package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.CatalogDTO;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.CatalogRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogService {

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;
    private final CatalogRepository catalogRepository;

    @Autowired
    @Lazy
    private CatalogService self;

    @Autowired
    public CatalogService(SerieServiceClient serieServiceClient, MovieServiceClient movieServiceClient, CatalogRepository catalogRepository) {
        this.serieServiceClient = serieServiceClient;
        this.movieServiceClient = movieServiceClient;
        this.catalogRepository = catalogRepository;
    }

    public CatalogDTO create(String genre){
        List<MovieServiceClient.MovieDto> listaPeliculas = self.findMoviesByGenre(genre);
        List<Serie>  listaSeries = self.findSeriesByGenre(genre);
        CatalogDTO c = new CatalogDTO(genre, listaPeliculas, listaSeries);
        return catalogRepository.save(c);
    }


    //CIRCUIT-BREAKER
    //Agregamos el circuit breaker en esta api ya que se encarga de conectar con la api de series y de movies
    //para armar el catalogo.

    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "catalogcircuit", fallbackMethod = "findMoviesFallBack")
    public List<MovieServiceClient.MovieDto> findMoviesByGenre(String genre) {
        var movies = movieServiceClient.getMovieByGenre(genre);
        return movies;
    }

    public List<MovieServiceClient.MovieDto> findMoviesFallBack(String genre, Throwable t) throws Exception {
        catalogRepository.findByGenre(genre);
        System.out.println("API-MOVIE-DOWN");
        throw new Exception("Movie not found");
    }


    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "catalogcircuit", fallbackMethod = "findSeriesFallBack")
    public List<Serie> findSeriesByGenre(String genre) {
        var series = serieServiceClient.getSerieByGenre(genre);
        return series;
    }

    public List<Serie> findSeriesFallBack(String genre, Throwable t) throws Exception {
        catalogRepository.findByGenre(genre);
        System.out.println("API-SERIE-DOWN");
        throw new Exception("Serie Not found");
    }







}
