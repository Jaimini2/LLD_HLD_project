package com.example.bookmyshow.dtos;

import com.example.bookmyshow.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatIds;

    private Long showId;

    private Long userId;
}
