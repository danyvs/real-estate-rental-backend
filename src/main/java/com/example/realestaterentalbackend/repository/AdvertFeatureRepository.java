package com.example.realestaterentalbackend.repository;

import com.example.realestaterentalbackend.model.AdvertFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertFeatureRepository extends JpaRepository<AdvertFeature, Integer> {

}
