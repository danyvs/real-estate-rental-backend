package com.example.realestaterentalbackend.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "features")
    List<AdvertFeature> advertFeature;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdvertFeature> getAdvertFeature() {
        return advertFeature;
    }
    public void setAdvertFeature(List<AdvertFeature> advertFeature) {
        this.advertFeature = advertFeature;
    }
}
