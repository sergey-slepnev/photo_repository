package com.slepnev.stockphoto.dto;

import java.math.BigDecimal;

public record PhotoDTO(String photoTheme, String resolution, BigDecimal cost) {
}