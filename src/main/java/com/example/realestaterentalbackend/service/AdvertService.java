package com.example.realestaterentalbackend.service;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.model.*;
import com.example.realestaterentalbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertImageRepository advertImageRepository;
    private final AdvertFeatureRepository advertFeatureRepository;
    private final FeatureRepository featureRepository;
    UserRepository userRepository;

    @Autowired
    public AdvertService(AdvertRepository advertRepository, AdvertImageRepository advertImageRepository,
                         AdvertFeatureRepository advertFeatureRepository, FeatureRepository featureRepository) {
        this.advertRepository = advertRepository;
        this.advertImageRepository = advertImageRepository;
        this.advertFeatureRepository = advertFeatureRepository;
        this.featureRepository = featureRepository;
    }

    public void addNewAdvert(AdvertDto advertDto, User user) {

        Advert advert = new Advert();
        advert.setTitle(advertDto.getTitle());
        advert.setPrice(advertDto.getPrice());
        advert.setRooms(advertDto.getRooms());
        advert.setTotalArea(advertDto.getTotalArea());
        advert.setUsableArea(advertDto.getUsableArea());
        advert.setOrientation(advertDto.getOrientation());
        advert.setYear(advertDto.getYear());
        advert.setFloor(advertDto.getFloor());
        advert.setFloorsBuilding(advertDto.getFloorsBuilding());
        advert.setPropertyType(advertDto.getPropertyType());
        advert.setViews(0);
        advert.setOwnerName(user.getFirstName() + " " + user.getLastName());
        advert.setLocation(advertDto.getLocation());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        advert.setPublicationDate(dateTimeFormatter.format(LocalDateTime.now()));
        advert.setUser(user);

        advertRepository.save(advert);

        for (String image : advertDto.getImages()) {
            AdvertImage advertImage = new AdvertImage();
            advertImage.setImage(image);
            advertImage.setAdvert(advert);
            advertImageRepository.save(advertImage);
        }

        for (String feature : advertDto.getAdvertFeatures()) {
            //if feaature exist in table features
            Feature feature1 = featureRepository.findById(Integer.parseInt(feature));
            if (feature1 != null ) {
                AdvertFeature advertFeature = new AdvertFeature();
                advertFeature.setAdvertI(Integer.parseInt(feature));
                advertFeature.setAdvert(advert);
                advertFeatureRepository.save(advertFeature);
            }

        }
    }
}
