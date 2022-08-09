package com.slepnev.stockphoto.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record UploadPhotoDTO(Long id,
                             String photoTheme,
                             String photoFormat,
                             String resolution,
                             Integer photographerId,
                             Double size,
                             Boolean isFree,
                             BigDecimal cost,
                             LocalDateTime createdAt,
                             Part photo) {
}
