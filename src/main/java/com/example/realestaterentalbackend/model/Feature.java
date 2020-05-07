package com.example.realestaterentalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "feature")
    @JsonIgnore
    private List<AdvertFeature> advertFeatures;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdvertFeature> getAdvertFeatures() {
        return advertFeatures;
    }

    public void setAdvertFeatures(List<AdvertFeature> advertFeature) {
        this.advertFeatures = advertFeature;
    }
}
