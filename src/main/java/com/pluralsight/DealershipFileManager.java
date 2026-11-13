package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class DealershipFileManager {

    public static Dealership getDealership() {
        String filePath = "src/main/resources/inventory.csv";
        Dealership newDealer = null;

        try {
            BufferedReader bufreader = new BufferedReader(new FileReader(filePath));
            String[] header = bufreader.readLine().split("\\|");
            newDealer = new Dealership(header[0], header[1], header[2]);

            String input;
            while((input = bufreader.readLine()) != null) {
                String[] tokens = input.split("\\|");

                int vin = Integer.parseInt(tokens[0]);
                int year = Integer.parseInt(tokens[1]);
                String make = tokens[2];
                String model = tokens[3];
                String vehicleType = tokens[4];
                String color = tokens[5];
                int odometer = Integer.parseInt(tokens[6]);
                BigDecimal price = new BigDecimal(tokens[7]);

                Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                newDealer.addVehicle(v);
            }

            bufreader.close();

        } catch (IOException ioe) {
            System.out.println("Error reading file:" + ioe.getMessage());
        }

        //done: Change to Dealership
        return newDealer;
    }

    public static void saveDealership(Dealership dealership){

    }
}
