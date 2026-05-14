package com.upc.pre.peaceapp.user.domain.services;

import com.upc.pre.peaceapp.user.domain.model.aggregates.UserProfile;
import com.upc.pre.peaceapp.user.domain.model.commands.CreateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.UpdateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<UserProfile> handle(CreateUserCommand command);
    Optional<UserProfile> handle(UpdateUserCommand command);
    void handle(DeleteUserCommand command);
}
