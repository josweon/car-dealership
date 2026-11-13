package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<>();
    }

    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        ArrayList<Vehicle> applyFilter = new ArrayList<>();
        for (Vehicle v : this.inventory) {
            if (v.getPrice().compareTo(min) >= 0 && v.getPrice().compareTo(max) <= 0) {
                applyFilter.add(v);
            }
        }
        return applyFilter;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> applyFilter = new ArrayList<>();
        for (Vehicle v : this.inventory) {
            if (v.getMake().equalsIgnoreCase(make) || v.getModel().equalsIgnoreCase(model)){
                applyFilter.add(v);
            }
        }
        return applyFilter;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> applyFilter = new ArrayList<>();
        for(Vehicle v : this.inventory) {
            if(v.getYear() >= min && v.getYear() <= max) {
                applyFilter.add(v);
            }
        }
        return applyFilter;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {

    }

    //region Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //endregion
}
