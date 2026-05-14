package com.upc.pre.peaceapp.user.application.internal.commandservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.UserProfile;
import com.upc.pre.peaceapp.user.domain.model.commands.CreateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.UpdateUserCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;
import com.upc.pre.peaceapp.user.domain.services.UserCommandService;
import com.upc.pre.peaceapp.user.infrastructure.persistence.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserProfile> handle(CreateUserCommand command) {
        log.info("Creating new user with email: {}", command.email());

        var user = new UserProfile(
                command.name(),
                command.email(),
                command.lastname(),
                command.phonenumber(),
                command.userId(),
                command.profileImage()
        );

        var savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return Optional.of(savedUser);
    }

    @Override
    public Optional<UserProfile> handle(UpdateUserCommand command) {
        log.info("Updating user with ID: {}", command.id());

        var user = userRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setName(command.name());
        user.setEmail(command.email());
        user.setLastname(command.lastname());
        user.setPhonenumber(command.phonenumber());
        user.setUserId(command.userId());
        user.setProfileImage(command.profileImage());

        var updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", updatedUser.getId());
        return Optional.of(updatedUser);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        log.info("Deleting user with ID: {}", command.id());

        if (!userRepository.existsById(command.id())) {
            log.error("User with ID {} does not exist", command.id());
            throw new IllegalArgumentException("User not found");
        }

        userRepository.deleteById(command.id());
        log.info("User deleted successfully with ID: {}", command.id());
    }
}
