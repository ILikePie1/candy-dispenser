package com.example.candy_dispenser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PreDestroy; // Updated import


@RestController
@RequestMapping("/candy")
public class CandyDispenserController {

    private ArduinoCommunicator arduinoCommunicator;

    // Initialize the ArduinoCommunicator in the constructor
    public CandyDispenserController() {
        this.arduinoCommunicator = new ArduinoCommunicator("/dev/cu.usbmodemXXX");  // Update with correct port
    }

    @PostMapping("/dispense")
    public ResponseEntity<String> dispenseCandy(@RequestParam String type) {
        // Send the appropriate command to Arduino
        if (type.equals("peanut")) {
            arduinoCommunicator.sendCommand("peanut");
        } else if (type.equals("nonpeanut")) {
            arduinoCommunicator.sendCommand("nonpeanut");
        } else {
            return ResponseEntity.badRequest().body("Invalid candy type");
        }

        return ResponseEntity.ok("Candy dispensed: " + type);
    }

    @PreDestroy  // Ensure the serial port is closed when the application shuts down
    public void cleanup() {
        arduinoCommunicator.close();
    }
}


