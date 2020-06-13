package com.example.realestaterentalbackend.validation;

import com.example.realestaterentalbackend.dto.AdvertDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class AdvertValidator {
    private final String DOUBLE_PATTERN = "[0-9]*\\.?[0-9]*";
    private final String NUMBER_PATTERN = "[0-9]+";
    private static final String NAME_PATTERN = "^(?=.{2,40}$)[A-Za-z]+(?:[-'\\s][A-Za-z]+)*$";

    public boolean validDouble(double number) {
        String numberD = String.valueOf(number);
        if(number > 0.0){
            return numberD.matches(DOUBLE_PATTERN);
        }
        return false;
    }

    public boolean validUnsigned(int number) {
        String numberI = String.valueOf(number);
        if(number > 0) {
            return numberI.matches(NUMBER_PATTERN);
        }
        return false;
    }

    public boolean validYear(int year) {
        String numberYear = String.valueOf(year);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(year > 1800 && year < currentYear) {
            return numberYear.matches(NUMBER_PATTERN);
        }
        return false;
    }

    public boolean validString(String name) {
        return name.matches(NAME_PATTERN);
    }


    public List<String> validate(AdvertDto advertDto) {
        List<String> errors = new ArrayList<>();


        if (!validDouble(advertDto.getPrice())) {
            errors.add("The price is not positive or does not contain only digits");
        }

        if (!validUnsigned(advertDto.getRooms())) {
            errors.add("The number of rooms is not positive or does not contain only digits");
        }

        if (!validDouble(advertDto.getTotalArea())) {
            errors.add("Total Area is not positive or does not contain only digits");
        }

        if (!validDouble(advertDto.getUsableArea()) || advertDto.getUsableArea() > advertDto.getTotalArea()) {
            errors.add("Usable Area is not positive or does not contain only digits or is greater than Total Area");
        }

        if (!validYear(advertDto.getYear())) {
            errors.add("The year is not between 1800 and the current year or does not contain only digits");
        }

        if (!validUnsigned(advertDto.getFloor()) || advertDto.getFloor() > advertDto.getFloorsBuilding()) {
            errors.add("The floor is is greater than floors building or does not contain only digits");
        }

        if (!validUnsigned(advertDto.getFloorsBuilding())) {
            errors.add("The floors builing is not positive or does not contain only digits");
        }

        if (!validString(advertDto.getPropertyType())) {
            errors.add("The property type does not contain only letters");
        }

        if (!validString(advertDto.getLocation())) {
            errors.add("The location name does not contain only letters");
        }

        return errors;
    }
}
