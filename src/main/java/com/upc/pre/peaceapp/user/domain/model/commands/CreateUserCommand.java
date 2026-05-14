package com.upc.pre.peaceapp.user.domain.model.commands;

public record CreateUserCommand(String name, String email, String lastname, String phonenumber, String userId, String profileImage) {
    public CreateUserCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (lastname == null || lastname.isBlank())
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        if (phonenumber == null || phonenumber.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        if (userId == null || userId.isBlank())
            throw new IllegalArgumentException("User ID cannot be null or empty");
    }
}
