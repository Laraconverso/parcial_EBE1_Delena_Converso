package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import com.dh.catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SerieCreadaEventConsumer {

    private SerieRepository serieRepository;

    public SerieCreadaEventConsumer(SerieRepository serieRepository){
        this.serieRepository = serieRepository;
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE_CREADA)
    public void listen(SerieCreadaEventConsumer.Data message){
        Serie serie = new Serie();
        serie.setName(message.getName());
        serie.setGenre(message.getGenre());
        System.out.print("NOMBRE DE SERIE "+ message.name);
        serieRepository.save(serie);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String id;
        private String name;
        private String genre;
    }
}
