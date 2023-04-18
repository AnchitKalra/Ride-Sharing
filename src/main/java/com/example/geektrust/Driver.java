package com.example.geektrust;

public class Driver implements Comparable<Driver>{

    private String id;
    private Integer xCoord;
    private Integer yCoord;

    private Float distance;

    private Boolean isBooked;


    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getxCoord() {
        return xCoord;
    }

    public void setxCoord(Integer xCoord) {
        this.xCoord = xCoord;
    }

    public Integer getyCoord() {
        return yCoord;
    }

    public void setyCoord(Integer yCoord) {
        this.yCoord = yCoord;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    @Override
    public int compareTo(Driver o) {
        if(this.getDistance() < o.getDistance()) {
            return -1;
        }
        else if(this.getDistance() > o.getDistance()) {
            return 1;
        }
        else {
            return this.getId().compareTo(getId());
        }
    }
}
