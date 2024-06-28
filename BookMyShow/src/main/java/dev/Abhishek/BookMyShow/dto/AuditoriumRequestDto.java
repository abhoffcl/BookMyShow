package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Auditorium;
import dev.Abhishek.BookMyShow.model.constant.AuditoriumFeatures;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuditoriumRequestDto {
    private String name;
    private Integer capacity;
    private int theatreId;
    private List<AuditoriumFeatures>auditoriumFeatures;


}
