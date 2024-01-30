import Controller.TokenController;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TokenRepository;
import repositories.VehicleRepository;
import services.TokenService;

public class Client2 {
    public static void main(String[] args) {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TokenRepository tokenRepository = new TokenRepository();
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TokenService tokenService = new TokenService(gateRepository,
                vehicleRepository,parkingLotRepository, tokenRepository);

            TokenController tokenController = new TokenController(tokenService);
       //     tokenController.issueToken()


    }
}