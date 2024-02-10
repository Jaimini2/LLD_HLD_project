package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookMovieRequestDTO;
import com.example.bookmyshow.dtos.BookMovieResponseDTO;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    BookMovieResponseDTO bookTicket(BookMovieRequestDTO bookMovieRequestDTO){
        BookMovieResponseDTO response = new BookMovieResponseDTO();

        try{

         Booking booking =   bookingService.bookTicket(
                    bookMovieRequestDTO.getShowSeatIds(),
                    bookMovieRequestDTO.getShowId(),
                    bookMovieRequestDTO.getUserId()
            );
            response.setBookingId(booking.getId())
                    .setAmount(booking.getAmount())
                    .setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
