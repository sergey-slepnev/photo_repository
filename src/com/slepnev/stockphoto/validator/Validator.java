package com.slepnev.stockphoto.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
