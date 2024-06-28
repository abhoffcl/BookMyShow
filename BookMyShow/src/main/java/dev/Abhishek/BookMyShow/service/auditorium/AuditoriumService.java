package dev.Abhishek.BookMyShow.service.auditorium;

import dev.Abhishek.BookMyShow.dto.AuditoriumRequestDto;
import dev.Abhishek.BookMyShow.dto.AuditoriumResponseDto;
import dev.Abhishek.BookMyShow.model.Auditorium;
import org.springframework.http.ResponseEntity;

public interface AuditoriumService {
    public AuditoriumResponseDto createAuditorium(AuditoriumRequestDto auditoriumRequestDto);
    public Boolean deleteAuditorium(int id);
    public AuditoriumResponseDto getAuditorium(int id);
    public AuditoriumResponseDto updateAuditorium(int id,AuditoriumRequestDto auditoriumRequestDto);

}
