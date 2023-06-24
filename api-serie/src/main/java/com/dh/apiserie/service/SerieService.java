package com.dh.apiserie.service;

import com.dh.apiserie.event.SerieCreadaEventProducer;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;

    private final SerieCreadaEventProducer serieCreadaEventProducer;

    public SerieService(SerieRepository repository, SerieCreadaEventProducer serieCreadaEventProducer) {
        this.repository = repository;
        this.serieCreadaEventProducer = serieCreadaEventProducer;
    }


    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public Serie save(Serie serie) {
        return repository.save(serie);
    }

    @Transactional
    public String create(Serie entity) throws Exception {
        repository.save(entity);
        serieCreadaEventProducer.publishSerieCreada(new SerieCreadaEventProducer.Data(entity.getId(),entity.getName(), entity.getGenre()));
        return entity.getId().toString();
    }

}
