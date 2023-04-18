package com.example.geektrust;

import java.util.List;

public class Rider {


    private String id;
    private Integer xCoord;

    private Integer yCoord;

    private List<Driver> matchedList;

    private Integer destXCoord = -1;

    private Integer destYCoord;

    private Integer timeSpent;

    private Integer n;


    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getDestXCoord() {
        return destXCoord;
    }

    public void setDestXCoord(Integer destXCoord) {
        this.destXCoord = destXCoord;
    }

    public Integer getDestYCoord() {
        return destYCoord;
    }

    public void setDestYCoord(Integer destYCoord) {
        this.destYCoord = destYCoord;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
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

    public List<Driver> getMatchedList() {
        return matchedList;
    }

    public void setMatchedList(List<Driver> matchedList) {
        this.matchedList = matchedList;
    }
}
