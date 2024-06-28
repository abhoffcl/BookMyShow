package dev.Abhishek.BookMyShow.dto;

import dev.Abhishek.BookMyShow.model.Theatre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreRequestDto {
    public String name;
    public Integer cityId;
    public String address;


}
