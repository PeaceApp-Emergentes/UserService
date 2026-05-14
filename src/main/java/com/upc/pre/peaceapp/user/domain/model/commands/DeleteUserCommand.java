package com.upc.pre.peaceapp.user.domain.model.commands;

public record DeleteUserCommand(Long id) {
    public DeleteUserCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
        }
    }
}
