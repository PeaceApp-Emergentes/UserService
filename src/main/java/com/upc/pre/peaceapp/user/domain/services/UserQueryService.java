package com.upc.pre.peaceapp.user.domain.services;

import com.upc.pre.peaceapp.user.domain.model.aggregates.UserProfile;
import com.upc.pre.peaceapp.user.domain.model.queries.GetUserByEmailQuery;

import java.util.Optional;

public interface UserQueryService {
    Optional<UserProfile> handle(GetUserByEmailQuery query);
    boolean existsById(Long id);
}
