package com.upc.pre.peaceapp.user.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Municipality profile resource representation")
public record MunicipalityProfileResource(
        Long id,
        String municipalityName,
        String city,
        String district,
        String institutionalEmail,
        String phone,
        String userId,
        String profileImage
) {
}
