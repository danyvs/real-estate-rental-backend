package com.example.realestaterentalbackend.model;

import javax.persistence.*;

@Entity(name = "advert_features")
public class AdvertFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    Advert advert;

    @ManyToOne
    Feature features;

    public int getId() {
        return id;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Feature getFeatures() {
        return features;
    }

    public void setFeatures(Feature features) {
        this.features = features;
    }

}
