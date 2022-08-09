package com.slepnev.stockphoto.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePhotographerDTO {
    String username;
    String email;
    String password;
    String phoneNumber;
    String socialNetwork;
    String status;
}
