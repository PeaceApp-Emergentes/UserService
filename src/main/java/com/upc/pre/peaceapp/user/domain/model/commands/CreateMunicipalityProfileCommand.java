package com.upc.pre.peaceapp.user.domain.model.commands;

public record CreateMunicipalityProfileCommand(
        String municipalityName,
        String city,
        String district,
        String institutionalEmail,
        String phone,
        String userId,
        String profileImage
) {
}
