package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.commands.CreateMunicipalityProfileCommand;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.CreateMunicipalityProfileResource;

public class CreateMunicipalityProfileCommandFromResourceAssembler {
    public static CreateMunicipalityProfileCommand toCommand(CreateMunicipalityProfileResource resource) {
        return new CreateMunicipalityProfileCommand(
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
