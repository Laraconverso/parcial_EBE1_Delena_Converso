package com.dh.catalog.event;

import com.dh.catalog.repository.CatalogRepository;
import com.dh.catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.dh.catalog.config.RabbitMQConfig;

@Component
public class MovieCreadaEventConsumer {

    private CatalogService catalogService;
    private CatalogRepository catalogRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE_CREADO)
    public void listen(MovieCreadaEventConsumer.Data message){
        System.out.print("NOMBRE DE PELICULA "+ message.name);
        CatalogRepository.saveMsjMovie(message);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private Long id;
        private String name;
        private String genre;
    }


}
