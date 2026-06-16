package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.aggregates.MunicipalityProfile;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.MunicipalityProfileResource;

public class MunicipalityProfileResourceFromEntityAssembler {
    public static MunicipalityProfileResource toResource(MunicipalityProfile entity) {
        return new MunicipalityProfileResource(
                entity.getId(),
                entity.getMunicipalityName(),
                entity.getCity(),
                entity.getDistrict(),
                entity.getInstitutionalEmail(),
                entity.getPhone(),
                entity.getUserId(),
                entity.getProfileImage()
        );
    }
}
