package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.commands.UpdateUserCommand;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UpdateUserResource;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandFromResourceAssembler {

    public static UpdateUserCommand toCommand(Long id, UpdateUserResource r) {
        return new UpdateUserCommand(
                id,
                r.name(),
                r.email(),
                r.lastname(),
                r.phonenumber(),
                r.userId(),
                r.profileImage()
        );
    }

}
