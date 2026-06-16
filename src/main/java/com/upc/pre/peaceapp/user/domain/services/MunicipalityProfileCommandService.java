package com.upc.pre.peaceapp.user.domain.services;

import com.upc.pre.peaceapp.user.domain.model.aggregates.MunicipalityProfile;
import com.upc.pre.peaceapp.user.domain.model.commands.CreateMunicipalityProfileCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.UpdateMunicipalityProfileCommand;

import java.util.Optional;

public interface MunicipalityProfileCommandService {
    Optional<MunicipalityProfile> handle(CreateMunicipalityProfileCommand command);
    Optional<MunicipalityProfile> handle(UpdateMunicipalityProfileCommand command);
}
