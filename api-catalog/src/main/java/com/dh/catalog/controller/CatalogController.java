package com.dh.catalog.controller;

import com.dh.catalog.model.CatalogDTO;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

//	private final MovieServiceClient movieServiceClient;
//	private final SerieServiceClient serieServiceClient;
//
//	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
//		this.movieServiceClient = movieServiceClient;
//		this.serieServiceClient = serieServiceClient;
//	}
//
//	@GetMapping("/{genre}")
//	ResponseEntity<CatalogDTO> getGenre(@PathVariable String genre) {
//		List<MovieServiceClient.MovieDto> movies = movieServiceClient(genre);
//		List<Serie> series = serieServiceClient.getSerieByGenre(genre);
//
//		CatalogDTO response = new CatalogDTO(genre,movies, series);
//		return ResponseEntity.ok(response);
//	}
	private final CatalogService catalogService;


	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/{genre}")
	ResponseEntity<CatalogDTO> getGenre(@PathVariable String genre) {
		CatalogDTO catalogo = catalogService.create(genre);
		return ResponseEntity.ok(catalogo);
	}


}
