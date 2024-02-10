package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.ShowSeatType;
import com.example.bookmyshow.repositories.ShowSeatTypeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculatPrice(List<ShowSeat> showSeats, Show show){
        //1. find all show seat types for this show
       List<ShowSeatType> showSeatTypes =  showSeatTypeRepository.findAllByShow(show);

       int amount = 0;
       for(ShowSeat showSeat : showSeats){
           for(ShowSeatType showSeatType : showSeatTypes){
               if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                   amount+=showSeatType.getPrice();
               }
           }
       }
       return amount;
    }

    //show : 1
    //platinum : 100
    //Gold: 50
    //Silver : 25
}
