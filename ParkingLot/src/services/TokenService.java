package services;

import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TokenRepository;
import repositories.VehicleRepository;
import strategies.SlotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TokenService {

    GateRepository gateRepository;
    VehicleRepository vehicleRepository;
    ParkingLotRepository parkingLotRepository;

    TokenRepository tokenRepository;

    public TokenService(GateRepository gateRepository, VehicleRepository vehicleRepository,
                 ParkingLotRepository parkingLotRepository,TokenRepository tokenRepository){
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.tokenRepository = tokenRepository;
    }

    public Token issueToken(
            String vehicleNumber,
            String vehicleOwnerName,
            Long gateId,
            VehicleTypes vehicleType) {


        //1 . Create a Token
        Token token = new Token();
        token.setEntryTime(new Date());

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if(!gateOptional.isPresent()){
            throw new RuntimeException();
        }

        Gate gate = gateOptional.get();
        token.setGeneratedAt(gate);
        token.setGeneratedby(gate.getGateOperator());


        Optional<Vehicle>  vehicleOptional = vehicleRepository.findVehicleByNumber(vehicleNumber);

            Vehicle savedVehicle = null;
            if(!vehicleOptional.isPresent()){
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleName(vehicleOwnerName);
                vehicle.setVehicleType(vehicleType);
                vehicle.setVehicleNumber(vehicleNumber);

                savedVehicle = vehicleRepository.saveVehicle(vehicle);


            }
            token.setVehicle(savedVehicle);

            //2. Assign a spot
           SlotAssignmentStrategyType slotAssignmentStrategyType =
                   parkingLotRepository.findParkingLotByGate(gate).getSlotAssignmentStrategyType();


       ParkingSlot parkingSlot =  SlotAssignmentStrategyFactory.getSloAssignmentStrategyByType(slotAssignmentStrategyType)
                .getSlot(gate,vehicleType);

        token.setParkingSlot(parkingSlot);

        //3 . Return token
       Token savedToken = tokenRepository.saveToken(token);
       savedToken.setTokenNumber(savedToken.getId());

       return savedToken;
    }


    }

//DTO - Client -> Controller