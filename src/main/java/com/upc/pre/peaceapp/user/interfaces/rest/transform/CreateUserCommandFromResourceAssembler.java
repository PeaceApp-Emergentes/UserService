package com.upc.pre.peaceapp.user.interfaces.rest.transform;

import com.upc.pre.peaceapp.user.domain.model.commands.CreateUserCommand;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.CreateUserResource;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommand(CreateUserResource r) {
        return new CreateUserCommand(
                r.name(),
                r.email(),
                r.lastname(),
                r.phonenumber(),
                r.userId(),
                r.profileImage()
        );
    }
}
