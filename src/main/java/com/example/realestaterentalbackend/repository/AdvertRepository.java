package com.example.realestaterentalbackend.repository;

import com.example.realestaterentalbackend.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Integer> {
}
