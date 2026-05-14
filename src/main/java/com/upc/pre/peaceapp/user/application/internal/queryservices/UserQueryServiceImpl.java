package com.upc.pre.peaceapp.user.application.internal.queryservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.UserProfile;
import com.upc.pre.peaceapp.user.domain.model.queries.GetUserByEmailQuery;
import com.upc.pre.peaceapp.user.domain.services.UserQueryService;
import com.upc.pre.peaceapp.user.infrastructure.persistence.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
    @Override
    public Optional<UserProfile> handle(GetUserByEmailQuery query) {
        log.info("Fetching user by email: {}", query.email());
        return userRepository.findByEmail(query.email());
    }
}
