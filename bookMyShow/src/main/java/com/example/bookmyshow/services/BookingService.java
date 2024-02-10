package com.example.bookmyshow.services;

import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.BookingRepository;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {


    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;

    private BookingRepository bookingRepository;

    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            BookingRepository bookingRepository,
            PriceCalculatorService priceCalculatorService
    ){
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(
            List<Long> showSeatIds,
            Long showId,
            Long UserId
    ){
        //1 . get a user with UseriD

        Optional<User> userOptional = userRepository.findById(UserId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }

        User bookedBy = userOptional.get();
        //2 . Get the corresponding shows with showId

        Optional<Show> showOptional = showRepository.findById(showId);

        Show savedShow = showOptional.get();
        //--------------TAKE LOCK---------
        //3. Get showseat with showseatId

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        //4. check if all the show seats are available

        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.EMPTY) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED)
            && Duration.between(showSeat.getBlockedAt().toInstant(),new Date().toInstant()).toMinutes()>15))){
                //5 . IF not throw an error
                throw new RuntimeException("Not all selected seats are available");

            }
        }

        //6 . If yes , mark all show seats as blocked
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());

            //7. save updated show seats in the database
            showSeatRepository.save(showSeat);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }
        //-----------RELEASE LOCK------------
        //8 . create the blocking object and add the details

        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShow(savedShow);
        booking.setUser(bookedBy);
        booking.setShowSeats(savedShowSeats);
        booking.setAmount(priceCalculatorService.calculatPrice(booking.getShowSeats(),booking.getShow()));
        booking.setPayments(new ArrayList<>());

        //9 . save it in the db
        //10. return the blocking object
        return bookingRepository.save(booking);

    }
}
