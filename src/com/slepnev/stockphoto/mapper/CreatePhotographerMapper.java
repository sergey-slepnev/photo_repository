package com.slepnev.stockphoto.mapper;

import com.slepnev.stockphoto.dto.CreatePhotographerDTO;
import com.slepnev.stockphoto.entity.PhotographerEntity;

public class CreatePhotographerMapper implements Mapper<CreatePhotographerDTO, PhotographerEntity> {

    private static final CreatePhotographerMapper INSTANCE = new CreatePhotographerMapper();

    @Override
    public PhotographerEntity mapFrom(CreatePhotographerDTO object) {
        return PhotographerEntity.builder()
                .username(object.getUsername())
                .email(object.getEmail())
                .password(object.getPassword())
                .phoneNumber(object.getPhoneNumber())
                .socialNetwork(object.getSocialNetwork())
                .status(object.getStatus())
                .build();
    }

    public static CreatePhotographerMapper getInstance() {
        return INSTANCE;
    }
}
