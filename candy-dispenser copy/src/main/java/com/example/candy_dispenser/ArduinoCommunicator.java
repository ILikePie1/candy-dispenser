package com.example.candy_dispenser;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.OutputStream;

public class ArduinoCommunicator {

    private SerialPort serialPort;

    // Constructor to initialize the serial port
    public ArduinoCommunicator(String portName) {
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(9600);  // Set baud rate to match Arduino settings

        // Attempt to open the serial port
        if (serialPort.openPort()) {
            System.out.println("Serial port opened successfully.");
        } else {
            System.out.println("Failed to open serial port.");
        }
    }

    // Method to send commands to the Arduino
    public void sendCommand(String command) {
        if (serialPort.isOpen()) {
            try {
                // Get the output stream
                OutputStream outputStream = serialPort.getOutputStream();

                // Send appropriate command based on user input (peanut or non-peanut)
                if (command.equals("peanut")) {
                    outputStream.write("P".getBytes());  // Send "P" for peanut door
                } else if (command.equals("nonpeanut")) {
                    outputStream.write("N".getBytes());  // Send "N" for non-peanut door
                }

                // Flush the output stream to ensure the data is sent
                outputStream.flush();
                System.out.println("Command sent: " + command);  // Log the sent command
            } catch (IOException e) {
                e.printStackTrace();  // Print stack trace for debugging
            }
        } else {
            System.out.println("Serial port is not open.");
        }
    }

    // Method to close the serial port when done
    public void close() {
        if (serialPort.isOpen()) {
            serialPort.closePort();
            System.out.println("Serial port closed.");
        }
    }
}
