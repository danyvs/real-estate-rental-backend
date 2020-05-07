package com.example.realestaterentalbackend.repository;

import com.example.realestaterentalbackend.model.AdvertImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertImageRepository extends JpaRepository<AdvertImage, Integer> {

}
