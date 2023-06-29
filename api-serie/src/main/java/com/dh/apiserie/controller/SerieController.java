package com.dh.apiserie.controller;

import com.dh.apiserie.event.SerieCreadaEventProducer;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private SerieService serieService;
    private final SerieCreadaEventProducer serieCreadaEventProducer;

    public SerieController(SerieService serieService, SerieCreadaEventProducer serieCreadaEventProducer) {
        this.serieService = serieService;
        this.serieCreadaEventProducer = serieCreadaEventProducer;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre){
        return ResponseEntity.ok(serieService.getSeriesByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Serie> createNewSerie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(serieService.createSerie(serie));
    }

}
