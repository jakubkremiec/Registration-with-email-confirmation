package pl.kremiec.registrationapp.service_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kremiec.registrationapp.service.RestService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class restService_test {

    @Autowired
    RestService restService;

    @Test
    public void CheckIfEmailIsCorrect(){

        assertEquals(true, restService.emailCheck("email@wp.pl"));
        assertEquals(true, restService.emailCheck("JakubKremiec@icloud.com"));
        assertEquals(false, restService.emailCheck("@wp.pl"));
        assertEquals(false, restService.emailCheck("email@wppl"));
        assertEquals(false, restService.emailCheck("@"));

    }
}
