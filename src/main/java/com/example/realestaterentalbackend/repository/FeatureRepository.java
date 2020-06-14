package com.example.realestaterentalbackend.repository;

import com.example.realestaterentalbackend.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    Optional<Feature> findById(int id);
}
