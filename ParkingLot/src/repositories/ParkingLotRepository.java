package repositories;

import models.Gate;
import models.ParkingLot;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotRepository {

    private Map<Long,ParkingLot> parkingLots = new TreeMap<>();
    public ParkingLot findParkingLotByGate(Gate gateID){

        for(ParkingLot parkingLot : parkingLots.values()){
            if(parkingLot.getGates().contains(gateID)){
                return parkingLot;
            }
        }
        return null;
    }
}
