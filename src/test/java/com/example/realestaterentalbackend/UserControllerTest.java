package com.example.realestaterentalbackend;

import com.example.realestaterentalbackend.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = RealEstateRentalBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public UserControllerTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    public void testRegisterUserValid() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Root");
        userDto.setLastName("Root");
        userDto.setEmail("goodygood@yahoo.com");
        userDto.setPassword("QWErty123*");
        userDto.setMatchingPassword("QWErty123*");
        userDto.setPhoneNumber("0712341234");

        ResponseEntity<String> response = this.testRestTemplate
                .postForEntity("http://localhost:" + port + "/user/register", userDto, String.class);

        assert(response.getStatusCodeValue() == 200);
    }

    @Test
    public void testRegisterUserWrong() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Root");
        userDto.setLastName("Root");
        userDto.setEmail("lalala@yahoo.com");
        userDto.setPassword("QWErty123*");
        userDto.setMatchingPassword("QWErty123*");
        userDto.setPhoneNumber("0712341234");

        ResponseEntity<String> response = this.testRestTemplate
                .postForEntity("http://localhost:" + port + "/user/register", userDto, String.class);

        assert(response.getStatusCode() == HttpStatus.BAD_REQUEST);
    }
}
