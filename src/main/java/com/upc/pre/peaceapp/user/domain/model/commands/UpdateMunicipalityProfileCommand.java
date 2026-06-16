package com.upc.pre.peaceapp.user.domain.model.commands;

public record UpdateMunicipalityProfileCommand(
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
