package com.example.realestaterentalbackend.repository;

import com.example.realestaterentalbackend.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    Feature findById(int id);
}
