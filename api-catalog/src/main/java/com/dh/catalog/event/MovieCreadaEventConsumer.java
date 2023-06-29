package com.dh.catalog.event;

import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.dh.catalog.config.RabbitMQConfig;

@Component
public class MovieCreadaEventConsumer {

    private MovieRepository movieRepository;

    public MovieCreadaEventConsumer(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE_CREADA)
    public void listen(MovieCreadaEventConsumer.Data message){
        Movie movie = new Movie();
        movie.setName(message.getName());
        movie.setGenre(message.getGenre());
        movie.setId(message.getId().toString());

        System.out.print("NOMBRE DE PELICULA "+ message.name);
        movieRepository.save(movie);
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
