package com.upc.pre.peaceapp.user.interfaces.rest;

import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;
import com.upc.pre.peaceapp.user.domain.services.UserCommandService;
import com.upc.pre.peaceapp.user.domain.services.UserQueryService;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.CreateUserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UpdateUserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Operations related to user management")
public class UserController {

    private final UserCommandService commandService;
    private final UserQueryService queryService;

    public UserController(UserCommandService commandService, UserQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserResource body) {
        var user = commandService.handle(CreateUserCommandFromResourceAssembler.toCommand(body));

        return user.<ResponseEntity<?>>map(u ->
                ResponseEntity.status(HttpStatus.CREATED)
                        .body(UserResourceFromEntityAssembler.toResource(u))
        ).orElseGet(() ->
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"message\":\"User could not be created\"}")
        );
    }
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> userExists(@PathVariable Long id) {
        boolean exists = queryService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateUserResource body) {
        var updated = commandService.handle(UpdateUserCommandFromResourceAssembler.toCommand(id, body));

        return updated.<ResponseEntity<?>>map(u ->
                ResponseEntity.ok(UserResourceFromEntityAssembler.toResource(u))
        ).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"User not found\"}")
        );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        return queryService.handle(new com.upc.pre.peaceapp.user.domain.model.queries.GetUserByEmailQuery(email))
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResource(u)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"User not found\"}"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            commandService.handle(new DeleteUserCommand(id));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }

}
