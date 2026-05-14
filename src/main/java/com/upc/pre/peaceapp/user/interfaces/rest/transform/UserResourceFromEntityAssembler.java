package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.aggregates.UserProfile;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UserResource;
import org.springframework.stereotype.Component;

@Component
public class UserResourceFromEntityAssembler {

    public static UserResource toResource(UserProfile user) {
        return new UserResource(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPhonenumber(),
                user.getUserId(),
                user.getProfileImage()
        );
    }
}
