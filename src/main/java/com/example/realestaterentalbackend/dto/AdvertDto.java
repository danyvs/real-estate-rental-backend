package com.example.realestaterentalbackend.dto;

import java.util.List;

public class AdvertDto {
    private String title;
    private double price;
    private int rooms;
    private double totalArea;
    private double usableArea;
    private char orientation;
    private int year;
    private int floor;
    private int floorsBuilding;
    private String propertyType;
    private String location;
    private List<String> images;
    private boolean[] features;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public double getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(double usableArea) {
        this.usableArea = usableArea;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloorsBuilding() {
        return floorsBuilding;
    }

    public void setFloorsBuilding(int floorsBuilding) {
        this.floorsBuilding = floorsBuilding;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean[] getFeatures() {
        return features;
    }

    public void setFeatures(boolean[] features) {
        this.features = features;
    }
}
