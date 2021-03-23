package sample.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.dto.OperatorAuthenticationResultDto;
import sample.dto.OperatorCredentialsDto;

public class AuthenticatorImpl implements Authenticator {

    private static final String AUTHENTICATION_URL = "http://localhost:8080/verify_operator_credentials";
    private RestTemplate restTemplate;

    public AuthenticatorImpl(){
       restTemplate = new RestTemplate();
    }

    @Override
    public void authenticate(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {
        Runnable authenticationTask = () -> {
            processAuthentication(operatorCredentialsDto, authenticationResultHandler);
        };
        Thread authentiactionThread = new Thread(authenticationTask);
        authentiactionThread.setDaemon(true);
        authentiactionThread.start();
    }

    private void processAuthentication(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {
        //ResponseEntity<OperatorAuthenticationResultDto> responseEntity = restTemplate.postForEntity(AUTHENTICATION_URL, operatorCredentialsDto, OperatorAuthenticationResultDto.class);
        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(true);
        dto.setFirstName("Krzysztof");
        dto.setLastName("Nowak");
        dto.setIdOperator(1L);
        authenticationResultHandler.handle(dto);
    }
}
