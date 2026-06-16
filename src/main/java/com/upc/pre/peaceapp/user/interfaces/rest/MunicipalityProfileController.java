package com.upc.pre.peaceapp.user.interfaces.rest;

import com.upc.pre.peaceapp.user.domain.model.queries.GetMunicipalityProfileByUserIdQuery;
import com.upc.pre.peaceapp.user.domain.services.MunicipalityProfileCommandService;
import com.upc.pre.peaceapp.user.domain.services.MunicipalityProfileQueryService;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.CreateMunicipalityProfileResource;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UpdateMunicipalityProfileResource;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.CreateMunicipalityProfileCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.MunicipalityProfileResourceFromEntityAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UpdateMunicipalityProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles/municipalities")
@Tag(name = "Municipality Profiles", description = "Operations related to municipality profile management")
public class MunicipalityProfileController {
    private final MunicipalityProfileCommandService commandService;
    private final MunicipalityProfileQueryService queryService;

    public MunicipalityProfileController(MunicipalityProfileCommandService commandService,
                                         MunicipalityProfileQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMunicipalityProfileResource body) {
        try {
            return commandService.handle(CreateMunicipalityProfileCommandFromResourceAssembler.toCommand(body))
                    .<ResponseEntity<?>>map(profile -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(MunicipalityProfileResourceFromEntityAssembler.toResource(profile)))
                    .orElseGet(() -> ResponseEntity.badRequest().body("{\"message\":\"Municipality profile could not be created\"}"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId) {
        return queryService.handle(new GetMunicipalityProfileByUserIdQuery(userId))
                .<ResponseEntity<?>>map(profile -> ResponseEntity.ok(MunicipalityProfileResourceFromEntityAssembler.toResource(profile)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"Municipality profile not found\"}"));
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<?> getByDistrict(@PathVariable String district) {
        return queryService.findByDistrict(district)
                .<ResponseEntity<?>>map(profile -> ResponseEntity.ok(MunicipalityProfileResourceFromEntityAssembler.toResource(profile)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"Municipality profile not found for district\"}"));
    }

    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> existsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(queryService.existsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateMunicipalityProfileResource body) {
        try {
            return commandService.handle(UpdateMunicipalityProfileCommandFromResourceAssembler.toCommand(id, body))
                    .<ResponseEntity<?>>map(profile -> ResponseEntity.ok(MunicipalityProfileResourceFromEntityAssembler.toResource(profile)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("{\"message\":\"Municipality profile not found\"}"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
