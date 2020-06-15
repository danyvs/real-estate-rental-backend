package com.example.realestaterentalbackend.controller;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.Advert;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.repository.AdvertRepository;
import com.example.realestaterentalbackend.repository.UserRepository;
import com.example.realestaterentalbackend.request.AdvertRequest;
import com.example.realestaterentalbackend.service.AdvertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advert")
public class AdvertController {

    private final UserRepository userRepository;
    private final AdvertRequest advertRequest;
    private final AdvertService advertService;
    private final AdvertRepository advertRepository;

    public AdvertController(UserRepository userRepository, AdvertRequest advertRequest, AdvertService advertService, AdvertRepository advertRepository) {
        this.userRepository = userRepository;
        this.advertRequest = advertRequest;
        this.advertService = advertService;
        this.advertRepository = advertRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addNewAdvert(@Valid @RequestBody AdvertDto advertDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        try {
            advertRequest.validateDataAdvert(advertDto, user);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        advertService.addNewAdvert(advertDto, user);
        return ResponseEntity.ok("Create advert successful");
    }

    @GetMapping("/getAdverts")
    public List<Advert> getAdverts() {
        return advertRepository.findAll();
    }

    @GetMapping("/getAdvert/{id}")
    public Advert showAdvertToUpdate(@PathVariable int id) {
        Advert advert = advertRepository.findById(id).orElseThrow(
                () -> new CustomException("No advert with this id")
        );
        advert.setViews(advert.getViews() + 1);
        advertRepository.save(advert);
        return advert;
    }

    @PostMapping("/updateAdvert/{id}")
    public ResponseEntity<?> updateAdvert(@Valid @RequestBody AdvertDto advertDto, @PathVariable int id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        List<Advert> adverts = user.getAdvertList();
        for (Advert advert : adverts) {
            if (advert.getId() == id) {
                try {
                    advertDto.setId(id);
                    advertRequest.validateDataAdvert(advertDto, user);
                } catch (CustomException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }

                if (advertService.updateAdvert(advertDto, user)) {
                    return ResponseEntity.ok("Update successful");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Advert not found");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        if (!advertService.delete(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Advert not found");
        }
        return ResponseEntity.ok("Successfully deleted");
    }


}
