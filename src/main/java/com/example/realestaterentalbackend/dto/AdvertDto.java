package com.example.realestaterentalbackend.dto;

import java.util.Date;
import java.util.List;

public class AdvertDto {
    private int id;
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
    private int views;
    private String location;
    private String ownerName;
    private Date publicationDate;

    private List<String> images;
    private List<String> advertFeatures;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getRooms() {
        return rooms;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getUsableArea() {
        return usableArea;
    }

    public char getOrientation() {
        return orientation;
    }

    public int getYear() {
        return year;
    }

    public int getFloor() {
        return floor;
    }

    public int getFloorsBuilding() {
        return floorsBuilding;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public int getViews() {
        return views;
    }

    public String getLocation() {
        return location;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getAdvertFeatures() {
        return advertFeatures;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public void setUsableArea(double usableArea) {
        this.usableArea = usableArea;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setFloorsBuilding(int floorsBuilding) {
        this.floorsBuilding = floorsBuilding;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setAdvertFeatures(List<String> advertFeatures) {
        this.advertFeatures = advertFeatures;
    }
}
