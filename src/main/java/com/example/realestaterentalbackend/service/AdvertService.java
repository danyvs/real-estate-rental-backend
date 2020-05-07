package com.example.realestaterentalbackend.service;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.model.Advert;
import com.example.realestaterentalbackend.model.AdvertImage;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.AdvertFeatureRepository;
import com.example.realestaterentalbackend.repository.AdvertImageRepository;
import com.example.realestaterentalbackend.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertImageRepository advertImageRepository;
    private final AdvertFeatureRepository advertFeatureRepository;

    @Autowired
    public AdvertService(AdvertRepository advertRepository, AdvertImageRepository advertImageRepository,
                         AdvertFeatureRepository advertFeatureRepository) {
        this.advertRepository = advertRepository;
        this.advertImageRepository = advertImageRepository;
        this.advertFeatureRepository = advertFeatureRepository;
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

        // TODO: implement features
    }
}
