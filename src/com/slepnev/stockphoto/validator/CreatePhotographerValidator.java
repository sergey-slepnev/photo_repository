package com.slepnev.stockphoto.validator;

import com.slepnev.stockphoto.dto.CreatePhotographerDTO;

public class CreatePhotographerValidator implements Validator<CreatePhotographerDTO> {

    private static final CreatePhotographerValidator INSTANCE = new CreatePhotographerValidator();

    @Override
    public ValidationResult isValid(CreatePhotographerDTO object) {
        var validationResult = new ValidationResult();
        if (object.getUsername().length() == 0) {
            validationResult.add(Error.of("invalid.username", "The field username cannot be empty"));
        }
        return validationResult;
    }

    public static CreatePhotographerValidator getInstance() {
        return INSTANCE;
    }
}