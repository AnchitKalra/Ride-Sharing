package com.example.geektrust;

import java.util.*;

public class Service {


   private List<Rider> riderList;
    private List<Driver> driverList;
   private List<Rider> matchedList;
   private Map<String, Rider> rideIdList;


    public Service() {
        riderList = new ArrayList<>();
        driverList = new ArrayList<>();
        matchedList = new ArrayList<>();
        rideIdList = new HashMap<>();
    }
/*
This method uses switch case to handle different scenarios and pass them to relevant methode\s
 */

    public void billing(String s[]) {
        String rideId = s[1];
        if(!rideIdList.containsKey(rideId)) {
            System.out.println("INVALID_RIDE");
        }
        else {
            Rider rider = rideIdList.get(rideId);
            int x1 = rider.getxCoord();
            int y1 = rider.getyCoord();
            int x2 = rider.getDestXCoord();
            if(x2 == -1) {
                System.out.println("RIDE_NOT_COMPLETED");
                return;
            }
            int y2 = rider.getDestYCoord();
            int xDist = Math.abs(x1 - x2);
            int yDist = Math.abs(y1 - y2);
            xDist *= xDist;
            yDist *= yDist;
            double dist = xDist + yDist;
            dist = Math.sqrt(dist);
            //System.out.println("DIST-->" + dist);
           // System.out.println("Distance->" + distance);
            String d = String.format("%.2f", dist);
            float distance = Float.parseFloat(d);
            //System.out.println("DISTANCE->" + distance);

            int baseFare = 50;
            float fare = 6.50f;
            fare *= distance;
            String f = String.format("%.2f", fare);
            fare = Float.parseFloat(f);
            fare += baseFare;
            float timeFair = 2.00f;
            int time = rider.getTimeSpent();
            timeFair *= time;
            fare += timeFair;
            String fares = String.format("%.2f", fare);
            fare = Float.parseFloat(fares);
            float serviceTax = 0.20f;
            serviceTax *= fare;
            String tax = String.format("%.2f", serviceTax);
            serviceTax = Float.parseFloat(tax);
            fare += serviceTax;
            String output = "";
            output += String.format("%.2f", fare);

            Driver driver = rider.getMatchedList().get(rider.getN() - 1);
            System.out.println("BILL " + rideId + " " + driver.getId() + " " + output);
        }

    }

    public void stopRide(String s[]) {
        String rideId = s[1];
        int x = Integer.parseInt(s[2]);
        int y = Integer.parseInt(s[3]);
        int time = Integer.parseInt(s[4]);
        if(time < 0) {
            System.out.println("INVALID_RIDE");
            return;
        }
        if (!rideIdList.containsKey(rideId)) {
            System.out.println("INVALID_RIDE");
        } else {

            Rider rider = rideIdList.get(rideId);
            Driver driver = rider.getMatchedList().get(rider.getN() - 1);
            if(!driver.getBooked()) {
                System.out.println("INVALID_RIDE");
            }
            else {
                driver.setBooked(false);
                rider.setDestXCoord(x);
                rider.setDestYCoord(y);
                rider.setTimeSpent(time);
                System.out.println("RIDE_STOPPED " + rideId);
            }
        }
    }

    public void addDriver(String s[]) {
        Driver driver = new Driver();
        String id = s[1];
        driver.setId(id);
        int x = Integer.parseInt(s[2]);
        driver.setxCoord(x);
        int y = Integer.parseInt(s[3]);
        driver.setyCoord(y);
        driver.setBooked(false);
        driverList.add(driver);
    }

    public void addRider(String s[]) {
        Rider rider = new Rider();
        String id = s[1];
        rider.setId(id);
        int x = Integer.parseInt(s[2]);
        rider.setxCoord(x);
        int y = Integer.parseInt(s[3]);
        rider.setyCoord(y);
        rider.setMatchedList(new ArrayList<>());
        riderList.add(rider);
    }

    public void match(String s[]) {
        String riderId = s[1];
        Rider r = new Rider();
        List<Driver> list = new ArrayList<>();
        for (Rider rider : riderList) {
            if (rider.getId().equals(riderId)) {
                r = rider;
                //list = r.getMatchedList();
                break;
            }
        }
        int x1 = r.getxCoord();
        int y1 = r.getyCoord();
        for (Driver driver : driverList) {
            if(driver.getBooked()) {
                continue;
            }
            int x2 = driver.getxCoord();
            int y2 = driver.getyCoord();
            int xDist = Math.abs(x1 - x2);
            int yDist = Math.abs(y1 - y2);
            xDist *= xDist;
            yDist *= yDist;
            double dist = xDist + yDist;
            dist = Math.sqrt(dist);
            String d = String.format("%.2f", dist);
             float distance =  Float.parseFloat(d);
            if (distance <= 5) {
                driver.setDistance(distance);
                list.add(driver);
            }
        }
        r.setMatchedList(list);
        if (r.getMatchedList().isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
        } else {
            Collections.sort(r.getMatchedList());
            System.out.print("DRIVERS_MATCHED ");
            int count = 0;
            for (Driver driver : r.getMatchedList()) {
                System.out.print(driver.getId() + " ");
                count++;
                if(count == 5)
                    break;
            }
            System.out.println();
        }
    }


    public void startRide(String s[]) {
        String rideId = s[1];
        int n = Integer.parseInt(s[2]);
        String riderId = s[3];
        Rider rider = new Rider();
        for (Rider r : riderList) {
            if (r.getId().equals(riderId)) {
                rider = r;
                break;
            }
        }
        List<Driver> drivers = rider.getMatchedList();
        if (rideIdList.containsKey(rideId)) {
            System.out.println("INVALID_RIDE");
        } else if (drivers.size() < n) {
            System.out.println("INVALID_RIDE");
        } else if (drivers.get(n - 1).getBooked()) {
            System.out.println("INVALID_RIDE");
        } else {
            rider.setN(n);
            Driver driver =  drivers.get(n - 1);
            driver.setBooked(true);
            //rider.setMatchedList(drivers);
            rideIdList.put(rideId, rider);
            System.out.println("RIDE_STARTED " + rideId);
        }
    }
}

