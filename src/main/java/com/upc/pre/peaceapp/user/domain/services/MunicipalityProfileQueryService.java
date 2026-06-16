package com.upc.pre.peaceapp.user.domain.services;

import com.upc.pre.peaceapp.user.domain.model.aggregates.MunicipalityProfile;
import com.upc.pre.peaceapp.user.domain.model.queries.GetMunicipalityProfileByUserIdQuery;

import java.util.Optional;

public interface MunicipalityProfileQueryService {
    Optional<MunicipalityProfile> handle(GetMunicipalityProfileByUserIdQuery query);
    Optional<MunicipalityProfile> findByDistrict(String district);
    boolean existsByUserId(String userId);
}
