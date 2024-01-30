package repositories;

import models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {

    private Map<Long,Gate> gates = new TreeMap<>();

   public Optional<Gate> findGateById(Long gateId){
        //db.execute();
        if(gates.containsKey(gateId)){
            return Optional.of(gates.get(gateId));
        }
        return null;
    }
}
