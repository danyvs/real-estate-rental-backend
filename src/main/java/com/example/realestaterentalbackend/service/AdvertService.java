package com.example.realestaterentalbackend.service;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.*;
import com.example.realestaterentalbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
            //if feature exist in table features
            Optional<Feature> feature1 = featureRepository.findById(Integer.parseInt(feature));
            if (feature1.isPresent()) {
                AdvertFeature advertFeature = new AdvertFeature();
                advertFeature.setAdvert(advert);
                advertFeatureRepository.save(advertFeature);
            }

        }
    }

    public boolean delete(int id) {
        Optional<Advert> optionalAdvert = advertRepository.findById(id);
        if (optionalAdvert.isPresent()) {
            Advert advert = optionalAdvert.get();
            List<AdvertFeature> advertFeatures = advert.getAdvertFeaturesList();
            for (AdvertFeature advertFeature : advertFeatures) {
                int idAdvertFeature = advertFeature.getId();
                advertFeatureRepository.deleteById(idAdvertFeature);
            }

            List<AdvertImage> advertImages = advert.getAdvertImagesList();
            for (AdvertImage advertImage : advertImages) {
                int idAdvertImage = advertImage.getId();
                advertImageRepository.deleteById(idAdvertImage);
            }

            advertRepository.delete(advert);
            return true;
        }
        return false;
    }

    public boolean updateAdvert(AdvertDto advertDto, User user) {
        Advert advert = advertRepository.findById(advertDto.getId()).orElseThrow(() -> new CustomException("Error"));
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
        advert.setOwnerName(user.getFirstName() + " " + user.getLastName());
        advert.setLocation(advertDto.getLocation());

        advertRepository.save(advert);

        for (String image : advertDto.getImages()) {
            AdvertImage advertImage = new AdvertImage();
            advertImage.setImage(image);
            advertImage.setAdvert(advert);
            advertImageRepository.save(advertImage);
        }

        for (String feature : advertDto.getAdvertFeatures()) {
            //if feature exist in table features
            Optional<Feature> feature1 = featureRepository.findById(Integer.parseInt(feature));
            if (feature1.isPresent()) {
                AdvertFeature advertFeature = new AdvertFeature();
                advertFeature.setAdvert(advert);
                advertFeatureRepository.save(advertFeature);
            }
        }
        return true;
    }
}