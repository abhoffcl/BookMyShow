package dev.Abhishek.BookMyShow.service.show;

import dev.Abhishek.BookMyShow.dto.ShowRequestDto;
import dev.Abhishek.BookMyShow.dto.ShowResponseDto;
import dev.Abhishek.BookMyShow.dto.ShowSeatResponseDto;
import dev.Abhishek.BookMyShow.exception.AuditoriumNotFoundException;
import dev.Abhishek.BookMyShow.exception.MovieNotFoundException;
import dev.Abhishek.BookMyShow.exception.ShowNotFoundException;
import dev.Abhishek.BookMyShow.model.*;
import dev.Abhishek.BookMyShow.repository.AuditoriumRepository;
import dev.Abhishek.BookMyShow.repository.MovieRepository;
import dev.Abhishek.BookMyShow.repository.ShowRepository;
import dev.Abhishek.BookMyShow.service.movie.MovieService;
import dev.Abhishek.BookMyShow.service.movie.MovieServiceImpl;
import dev.Abhishek.BookMyShow.service.showSeat.ShowSeatService;
import dev.Abhishek.BookMyShow.service.showSeat.ShowSeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowSeatService showSeatService;
    @Override
    public ShowResponseDto createShow(ShowRequestDto showRequestDto) {
        int auditoriumId=showRequestDto.getAuditoriumId() ;
        Auditorium savedAuditorium = auditoriumRepository.findById(auditoriumId).
                orElseThrow(()->new AuditoriumNotFoundException("Auditorium not found for id "+auditoriumId)) ;
        List<Show> Shows=savedAuditorium.getShows()!=null?savedAuditorium.getShows():new ArrayList<>();
        Show show= showRequestDtoToEntity(showRequestDto);
        Show savedShow= showRepository.save(show);
        Shows.add(show);
        savedAuditorium.setShows(Shows);
        auditoriumRepository.save(savedAuditorium);
        return entityToShowResponseDto(savedShow);
    }
    @Override
    public Boolean deleteShow(int id) {
        Show show = showRepository.findById(id).
                orElseThrow(()->new ShowNotFoundException("Show not found for id "+id));
        Auditorium auditorium = show.getAuditorium();
        if(auditorium==null)
            throw new AuditoriumNotFoundException("Auditorium not found for show id "+id);
        auditorium.getShows().remove(show);
        showRepository.delete(show);
        auditoriumRepository.save(auditorium);
        return true;
    }
    @Override
    public ShowResponseDto getShow(int id) {
        Show savedShow= showRepository.findById(id).
                orElseThrow(()->new ShowNotFoundException("Show not found for id "+id));
        return entityToShowResponseDto(savedShow);
    }
    @Override
    public ShowResponseDto updateShow(int id,ShowRequestDto showRequestDto) {
        Show savedShow= showRepository.findById(id).
                orElseThrow(()->new ShowNotFoundException("Show not found for id "+id));
        if(showRequestDto.getMovieId()>0) {
            Movie movie = movieRepository.findById(showRequestDto.getMovieId()).
                    orElseThrow(() -> new MovieNotFoundException("Movie not found for show id " + id));
            savedShow.setMovie(movie);
        }
        savedShow.setStartTime(showRequestDto.getStartTime());
        savedShow.setEndTime(showRequestDto.getEndTime());
        Show updatedShow=showRepository.save(savedShow);
        return entityToShowResponseDto(updatedShow);
    }
    public  Show showRequestDtoToEntity(ShowRequestDto showRequestDto){
        Show show = new Show();
        Movie movie = movieRepository.findById(showRequestDto.getMovieId()).
                orElseThrow(()-> new MovieNotFoundException("Movie not found for id "+showRequestDto.getMovieId()));
        Auditorium auditorium = auditoriumRepository.findById(showRequestDto.getAuditoriumId()).
                orElseThrow(()-> new AuditoriumNotFoundException("Auditorium not found for id "+showRequestDto.getAuditoriumId()));
        show.setAuditorium(auditorium);
        show.setMovie(movie);
        show.setStartTime(showRequestDto.getStartTime());
        show.setEndTime(showRequestDto.getEndTime());
        return show;
    }
    public  ShowResponseDto entityToShowResponseDto(Show show){
        ShowResponseDto showResponseDto = new ShowResponseDto();
        showResponseDto.setShowId(show.getId());
        showResponseDto.setAuditoriumId(show.getAuditorium().getId());
        showResponseDto.setStartTime(show.getStartTime());
        showResponseDto.setEndTime(show.getEndTime());
        showResponseDto.setMovie(((MovieServiceImpl)movieService).entityToMovieResponseDto(show.getMovie()));
        List<ShowSeat>showSeats =show.getShowSeats()!=null?show.getShowSeats(): new ArrayList<>();
        List<ShowSeatResponseDto>showSeatResponseDtos=new ArrayList<>();
        for(ShowSeat showSeat:showSeats){
            showSeatResponseDtos.add(((ShowSeatServiceImpl)showSeatService).entityToShowSeatResponseDto(showSeat));
        }
        showResponseDto.setShowSeats(showSeatResponseDtos);
        return showResponseDto;
    }
}
