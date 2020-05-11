package com.example.realestaterentalbackend.request;

import com.example.realestaterentalbackend.dto.AdvertDto;
import com.example.realestaterentalbackend.dto.UserDto;
import com.example.realestaterentalbackend.exception.CustomException;
import com.example.realestaterentalbackend.model.User;
import com.example.realestaterentalbackend.service.AdvertService;
import com.example.realestaterentalbackend.validator.AdvertValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertRequest {
    AdvertValidator advertValidator;
    AdvertService advertService;

    @Autowired
    public AdvertRequest(AdvertValidator advertValidator, AdvertService advertService) {
        this.advertValidator = advertValidator;
        this.advertService = advertService;
    }

    public void validateDataAdvert(AdvertDto advertDto, User user) throws CustomException {
        List<String> errors;
        errors = advertValidator.validate(advertDto);

        if (!errors.isEmpty()) {
            String errorMsg = errors.stream()
                    .collect(Collectors.joining(",\n"));
            throw  new CustomException(errorMsg);
        } else {
            advertService.addNewAdvert(advertDto, user);
        }
    }
}
