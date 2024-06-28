package dev.Abhishek.BookMyShow.service.actor;


import dev.Abhishek.BookMyShow.dto.ActorRequestDto;
import dev.Abhishek.BookMyShow.dto.ActorResponseDto;
import org.springframework.http.ResponseEntity;

public interface ActorService {
    public ActorResponseDto createActor(ActorRequestDto actorRequestDto);
    public Boolean deleteActor(int id);
    public ActorResponseDto getActor(int id);
    public ActorResponseDto updateActor(int id ,ActorRequestDto actorRequestDto);
}
