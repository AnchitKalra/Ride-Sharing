package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            Service service = new Service();

            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands

                String unprocessedString = sc.nextLine();
                String processedString[] = unprocessedString.split(" ");
                switch (processedString[0]) {
                    case "ADD_DRIVER":
                        service.addDriver(processedString);
                        break;
                    case "ADD_RIDER":
                        service.addRider(processedString);
                        break;
                    case "MATCH":
                        service.match(processedString);
                        break;
                    case "START_RIDE":
                        service.startRide(processedString);
                        break;
                    case "STOP_RIDE":
                        service.stopRide(processedString);
                        break;
                    case "BILL":
                        service.billing(processedString);
                }

            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
