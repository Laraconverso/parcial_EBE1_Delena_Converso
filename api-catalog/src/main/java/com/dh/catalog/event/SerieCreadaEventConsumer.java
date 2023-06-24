package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.repository.CatalogRepository;
import com.dh.catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SerieCreadaEventConsumer {
    private CatalogService catalogService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE_CREADO)
    public void listen(SerieCreadaEventConsumer.Data message){
        System.out.print("NOMBRE DE SERIE "+ message.name);
        CatalogRepository.saveMsjSerie(message);
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
