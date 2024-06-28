package dev.Abhishek.BookMyShow.service.movie;

import dev.Abhishek.BookMyShow.dto.ActorResponseDto;
import dev.Abhishek.BookMyShow.dto.MovieRequestDto;
import dev.Abhishek.BookMyShow.dto.MovieResponseDto;
import dev.Abhishek.BookMyShow.exception.ActorNotFoundException;
import dev.Abhishek.BookMyShow.exception.MovieNotFoundException;
import dev.Abhishek.BookMyShow.model.Actor;
import dev.Abhishek.BookMyShow.model.Movie;
import dev.Abhishek.BookMyShow.repository.ActorRepository;
import dev.Abhishek.BookMyShow.repository.MovieRepository;
import dev.Abhishek.BookMyShow.service.actor.ActorService;
import dev.Abhishek.BookMyShow.service.actor.ActorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private ActorService actorService;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto) {
        Movie savedMovie= movieRepository.save(MovieRequestDtoToEntity(movieRequestDto));
        return entityToMovieResponseDto(savedMovie);
    }
    @Override
    public Boolean deleteMovie(int id) {
        Movie movie= movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException("Movie not found for id "+id));
        movieRepository.delete(movie);
        return true;
    }

    @Override
    public MovieResponseDto getMovie(int id) {
        Movie savedMovie= movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException("Movie not found for id "+id));
        return entityToMovieResponseDto(savedMovie);
    }

    @Override
    public MovieResponseDto updateMovie(int id,MovieRequestDto movieRequestDto) {
        Movie savedMovie= movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException("Movie not found for id "+id));
        if(savedMovie.getName()!=null)
            savedMovie.setName(movieRequestDto.getName());
        if(savedMovie.getDescription()!=null)
            savedMovie.setDescription(movieRequestDto.getDescpription());
        Movie updateMovie=movieRepository.save(savedMovie);
        return entityToMovieResponseDto(updateMovie);
    }
    public Movie MovieRequestDtoToEntity(MovieRequestDto movieRequestDto){
        Movie movie = new Movie();
        List<Actor>actors=new ArrayList<>();
        for(int actorId:movieRequestDto.getActorIds()) {
           actors.add(actorRepository.findById(actorId).
                   orElseThrow(()->new ActorNotFoundException("Actor Not Found for id "+actorId)));
        }
        movie.setActors(actors);
        movie.setName(movieRequestDto.getName());
        movie.setDescription(movieRequestDto.getDescpription());
        movie.setMovieFeature(movieRequestDto.getFeature());
        movie.setReleaseDate(movieRequestDto.getReleaseDate());
        return movie;
    }
    public MovieResponseDto entityToMovieResponseDto(Movie movie){
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        List<Actor>actors=movie.getActors()!=null?movie.getActors():new ArrayList<>();
        List<ActorResponseDto>actorResponseDtos=new ArrayList<>();
        for(Actor actor:actors){
            actorResponseDtos.add(((ActorServiceImpl)actorService).entityToActorResponseDto(actor));
        }
        movieResponseDto.setActors(actorResponseDtos);
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setName(movie.getName());
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setMovieFeature(movie.getMovieFeature());
        return movieResponseDto;
    }
}
