package com.upc.pre.peaceapp.user.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resource used to update an existing user profile")
public record UpdateUserResource(

        @Schema(description = "User's first name", example = "User")
        String name,

        @Schema(description = "User's last name", example = "123")
        String lastname,

        @Schema(description = "User's email address", example = "user@example.com")
        String email,

        @Schema(description = "User's phone number", example = "987654321")
        String phonenumber,
        @Schema(description = "User's unique system identifier", example = "USR-001")
        String userId,

        @Schema(description = "Profile image URL", example = "https://example.com/images/profile1.jpg")
        String profileImage
) {}
