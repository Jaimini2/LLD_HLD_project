package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BookMovieResponseDTO {
    private ResponseStatus responseStatus;
    private int amount;
    //the booking is not finalised yet.
    //it will be finalised once payment is done

    private Long bookingId;

}
