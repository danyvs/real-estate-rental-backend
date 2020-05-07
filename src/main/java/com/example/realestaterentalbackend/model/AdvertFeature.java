package com.example.realestaterentalbackend.model;

import javax.persistence.*;

@Entity(name = "advert_features")
public class AdvertFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Advert advert;

    @ManyToOne
    private Feature feature;

    public int getId() {
        return id;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature features) {
        this.feature = features;
    }
}
