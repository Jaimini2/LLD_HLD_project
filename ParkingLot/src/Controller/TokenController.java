package Controller;

import DTOs.IssueTokenRequestDTO;
import DTOs.IssueTokenResponseDTO;
import DTOs.ResponseStatus;
import models.Token;
import services.TokenService;

/**
 * 1. Interacts with the client
 * 2. when Request comes , Validations can happen in Controller
 * 3.
 */
public class TokenController {

    private TokenService tokenService;


    public TokenController(TokenService tokenService){
        this.tokenService = tokenService;
    }


   public IssueTokenResponseDTO issueToken(IssueTokenRequestDTO issueTokenRequestDTO){
        IssueTokenResponseDTO issueTokenResponseDTO = new IssueTokenResponseDTO();
        try {


            Token token = tokenService.issueToken(issueTokenRequestDTO.getVehicleNumber(),
                    issueTokenRequestDTO.getVehicleOwnerName(),
                    issueTokenRequestDTO.getGateId(),
                    issueTokenRequestDTO.getVehicleType());
            issueTokenResponseDTO.setToken(token);
            issueTokenResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception ex){
            issueTokenResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            issueTokenResponseDTO.setFailureMessage(ex.getMessage());
        }

        return issueTokenResponseDTO;
    }
}
