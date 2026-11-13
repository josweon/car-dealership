package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    Scanner scanner = new Scanner(System.in);

    private void init() {
        this.dealership = DealershipFileManager.getDealership();
    }

    public void display() {
        init();
        String menu = """
                ==* MENU *==
                [1] - Find vehicles within a price range\s
                [2] - Find vehicles by make / model\s
                [3] - Find vehicles by year range\s
                [4] - Find vehicles by color\s
                [5] - Find vehicles by mileage range\s
                [6] - Find vehicles by type (car, truck, SUV, van)\s
                [7] - List ALL vehicles\s
                [8] - Add a vehicle\s
                [9] - Remove a vehicle\s
                [99]- Quit
                
                ENTER option:\s""";

        String option;
        boolean exit = false;

        do {
            System.out.print(menu);
            option = scanner.nextLine().trim();

            switch(option) {
                case "1"-> processGetByPriceRequest();
                case "2"-> processGetByMakeModelRequest();
                case "3"-> System.out.print("processGetByYearRequest()");
                case "4"-> System.out.print("processGetByColorRequest()");
                case "5"-> System.out.print("processGetByMileageRequest()");
                case "6"-> System.out.print("processGetByVehicleTypeRequest()");
                case "7"-> processGetAllVehiclesRequest();
                case "8"-> System.out.print("processAddVehicleRequest()");
                case "9"-> System.out.print("processRemoveVehicleRequest()");
                case "99"-> {
                    System.out.println("\nBye Bye :)");
                    exit = true;
                }
                default -> System.out.println("⚠\uFE0F Not an option: Try again\n");
            }

        } while (!exit);
    }

    //done: change to private
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        //done: create a loop and display the vehicles;
        System.out.print("\n");
        String closeTable = "-".repeat(94);

        String rowFormatHeading = "| %-5s | %-4s | %-10s | %-14s | %-10s | %-7s | %-8s | %-10s | %n";
        String rowFormat = "| %-5s | %-4s | %-10s | %-14s | %-11s | %-7s | %-8s | $%-9s | %n";

        System.out.println(closeTable);
        System.out.printf(rowFormatHeading, "vin", "year", "make", "model", "vehicleType", "color", "odometer", "price");
        System.out.println(closeTable);

        for (Vehicle v : vehicles) {
            System.out.printf(rowFormat,
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());
        }
        System.out.println(closeTable + "\n");
    }

    public void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processGetByPriceRequest() {
        System.out.println("\t[SEARCH] - Price");
        BigDecimal min = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;

        boolean valid = false;
        do {
            try {
                System.out.print("\tMin price: $");
                min = new BigDecimal(scanner.nextLine());

                System.out.print("\tMax price: $");
                max = new BigDecimal(scanner.nextLine());

                valid = true;

            } catch (NumberFormatException nfe) {
                System.out.println("\tNot a valid number: " + nfe.getMessage());
            }
        } while(!valid);

        if(this.dealership.getVehiclesByPrice(min, max).isEmpty()){
            System.out.println("\n\t=* no vehicles found *=\n");
        } else {
            displayVehicles(this.dealership.getVehiclesByPrice(min, max));
        }
    }

    public void processGetByMakeModelRequest() {
        System.out.println("\t[SEARCH] - Make & Model");
        String make = null;
        String model = null;

        boolean notValid;
        do {

            System.out.print("\tMake: ");
            make = scanner.nextLine();

            System.out.print("\tModel: ");
            model = scanner.nextLine();

            //done: find a better way to implement
            notValid = make == null || make.isBlank() || model == null || model.isBlank();
            if (notValid) {
                System.out.println("\tInput cannot be empty\n");
            }

        } while(notValid);

        if(this.dealership.getVehiclesByMakeModel(make,model).isEmpty()){
            System.out.println("\n\t=* no vehicles found *=\n");
        } else {
            displayVehicles(this.dealership.getVehiclesByMakeModel(make, model));
        }
    }
}