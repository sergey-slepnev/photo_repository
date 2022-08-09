package com.slepnev.stockphoto.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
