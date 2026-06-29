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
        // El id que llega de otros servicios es el id del IAM, guardado en la columna user_id.
        // Validamos por user_id y, como respaldo, por la clave primaria.
        return userRepository.existsByUserId(String.valueOf(id)) || userRepository.existsById(id);
    }
    @Override
    public Optional<UserProfile> findById(Long id) {
        log.info("Fetching user by id: {}", id);
        // Preferimos el id del IAM (columna user_id); si no, la clave primaria.
        Optional<UserProfile> byUserId = userRepository.findByUserId(String.valueOf(id));
        return byUserId.isPresent() ? byUserId : userRepository.findById(id);
    }
    @Override
    public Optional<UserProfile> handle(GetUserByEmailQuery query) {
        log.info("Fetching user by email: {}", query.email());
        return userRepository.findByEmail(query.email());
    }
}
