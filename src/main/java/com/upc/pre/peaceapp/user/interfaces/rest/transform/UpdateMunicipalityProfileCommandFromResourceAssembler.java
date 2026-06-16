package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.commands.UpdateMunicipalityProfileCommand;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UpdateMunicipalityProfileResource;

public class UpdateMunicipalityProfileCommandFromResourceAssembler {
    public static UpdateMunicipalityProfileCommand toCommand(Long id, UpdateMunicipalityProfileResource resource) {
        return new UpdateMunicipalityProfileCommand(
                id,
                resource.municipalityName(),
                resource.city(),
                resource.district(),
                resource.institutionalEmail(),
                resource.phone(),
                resource.userId(),
                resource.profileImage()
        );
    }
}
