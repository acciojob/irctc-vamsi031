package com.driver.services;


import com.driver.EntryDto.BookTicketEntryDto;
import com.driver.model.Station;
import com.driver.model.Train;
import com.driver.repository.PassengerRepository;
import com.driver.repository.TicketRepository;
import com.driver.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    PassengerRepository passengerRepository;


    public Integer bookTicket(BookTicketEntryDto bookTicketEntryDto)throws Exception{

        //Check for validity
        //Use bookedTickets List from the TrainRepository to get bookings done against that train
        // Incase the there are insufficient tickets
        // throw new Exception("Less tickets are available");
        //otherwise book the ticket, calculate the price and other details
        //Save the information in corresponding DB Tables
        //Fare System : Check problem statement
        //Incase the train doesn't pass through the requested stations
        //throw new Exception("Invalid stations");
        //Save the bookedTickets in the train Object
        //Also in the passenger Entity change the attribute bookedTickets by using the attribute bookingPersonId.
       //And the end return the ticketId that has come from db
        Optional<Train> optinalTrain = trainRepository.findById(bookTicketEntryDto.getTrainId());
        Train train = optinalTrain.get();
        if(train.getNoOfSeats()<bookTicketEntryDto.getNoOfSeats())throw new Exception("Less tickets are available");
        List<String> routs = Arrays.asList(train.getRoute().split(","));
        Boolean flag=false;
        int count=0;
        for(String r:routs){
            if(r.equals(bookTicketEntryDto.getFromStation()))flag=true;
            if(flag)count++;
            if(r.equals(bookTicketEntryDto.getToStation()))break;
        }

       return null;

    }
}
