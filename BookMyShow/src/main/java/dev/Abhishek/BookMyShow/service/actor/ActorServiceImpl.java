package dev.Abhishek.BookMyShow.service.actor;

import dev.Abhishek.BookMyShow.dto.ActorRequestDto;
import dev.Abhishek.BookMyShow.dto.ActorResponseDto;
import dev.Abhishek.BookMyShow.exception.ActorNotFoundException;
import dev.Abhishek.BookMyShow.model.Actor;
import dev.Abhishek.BookMyShow.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public ActorResponseDto createActor(ActorRequestDto actorRequestDto) {
        Actor savedActor= actorRepository.save(actorRequestDtoToEntity(actorRequestDto));
        return entityToActorResponseDto(savedActor);

    }

    @Override
    public Boolean deleteActor(int id) {
       Actor actor= actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException("Actor not found for id "+id));
       actorRepository.delete(actor);
        return true;
    }

    @Override
    public ActorResponseDto getActor(int id) {
        Actor savedActor= actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException("Actor not found for id "+id));
        return entityToActorResponseDto(savedActor);
    }

    @Override
    public ActorResponseDto updateActor(int id,ActorRequestDto actorRequestDto) {
        Actor savedActor= actorRepository.findById(id).orElseThrow(()->new ActorNotFoundException("Actor not found for id "+id));
        savedActor.setName(actorRequestDto.getName());
        Actor updateActor=actorRepository.save(savedActor);
        return entityToActorResponseDto(updateActor);

    }
    public Actor actorRequestDtoToEntity(ActorRequestDto actorRequestDto){
        Actor actor = new Actor();
        actor.setName(actorRequestDto.getName());
        return actor;
    }
    public ActorResponseDto entityToActorResponseDto(Actor actor){
        ActorResponseDto actorResponseDto = new ActorResponseDto();
        actorResponseDto.setId(actor.getId());
        actorResponseDto.setName(actor.getName());

        return actorResponseDto;
    }
}