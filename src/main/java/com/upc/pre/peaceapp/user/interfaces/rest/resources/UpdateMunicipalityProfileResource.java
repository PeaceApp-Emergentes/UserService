package com.upc.pre.peaceapp.user.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resource used to update a municipality profile")
public record UpdateMunicipalityProfileResource(
        String municipalityName,
        String city,
        String district,
        String institutionalEmail,
        String phone,
        String userId,
        String profileImage
) {
}
