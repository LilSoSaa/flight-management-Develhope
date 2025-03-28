package com.develhope.flight_management_develhope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/*all the string values are randomly generated (using random.ints())
for retrieving all the flights in the db
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PostMapping("/provision")
    public List<Flight> provisionFlights() {
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            Flight flight = new Flight(
                    generateRandomString(random, 10),  // Random name
                    generateRandomString(random, 15),  // Random description
                    generateRandomString(random, 3),   // Random fromAirport code
                    generateRandomString(random, 3)   // Random toAirport code
            );
            flightRepository.save(flight);
        }

        return flightRepository.findAll();
    }

    //Generates a random alphanumeric string of a given length.
    private String generateRandomString(Random random, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < length) {
            int randomChar = random.nextInt(75) + 48; // ASCII range 48-122
            if (Character.isLetterOrDigit(randomChar)) {
                stringBuilder.append((char) randomChar);
            }
        }
        return stringBuilder.toString();
    }
}
