package com.example.candy_dispenser;

import com.fazecast.jSerialComm.SerialPort;

public class ArduinoController {

    public static void main(String[] args) {
        // Find the serial port (this may vary based on your system)
        SerialPort arduinoPort = SerialPort.getCommPort("/dev/ttyUSB0"); // Adjust to your port
        arduinoPort.setBaudRate(9600);

        if (arduinoPort.openPort()) {
            System.out.println("Port is open!");

            // Write "yes" or "no" to the Arduino
            String input = "yes";  // Or get this input from your Java app
            arduinoPort.writeBytes(input.getBytes(), input.length());

            arduinoPort.closePort();
        } else {
            System.out.println("Failed to open port.");
        }
    }
}
