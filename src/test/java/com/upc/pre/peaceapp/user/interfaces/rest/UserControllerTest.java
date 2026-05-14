/*
package com.upc.pre.peaceapp.user.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pre.peaceapp.user.domain.model.commands.DeleteUserCommand;
import com.upc.pre.peaceapp.user.domain.services.UserCommandService;
import com.upc.pre.peaceapp.user.domain.services.UserQueryService;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.CreateUserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UpdateUserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.resources.UserResource;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.upc.pre.peaceapp.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    private static final String BASE = "/api/v1/users";

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private UserCommandService commandService;
    @MockBean private UserQueryService queryService;

    // ---------- POST /users ----------
    @Test
    @DisplayName("POST /users -> 201 Created con UserResource")
    void create_created() throws Exception {
        var req = new CreateUserResource(
                "Bryan", "bryan@upc.edu.pe", "Espejo", "999888777", package com.upc.pre.peaceapp.location.messaging.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class ReportApprovedEvent implements Serializable {

            private Long reportId;
            private Double latitude;
            private Double longitude;
            private String message;
            private LocalDateTime timestamp;

            public ReportApprovedEvent() {}
        }
        123L, "img.png"
        );
        var resource = new UserResource(
                7L, "Bryan", "Espejo", "bryan@upc.edu.pe", "999888777", 123L, "img.png"
        );

        try (MockedStatic<CreateUserCommandFromResourceAssembler> mCreate =
                     Mockito.mockStatic(CreateUserCommandFromResourceAssembler.class);
             MockedStatic<UserResourceFromEntityAssembler> mRes =
                     Mockito.mockStatic(UserResourceFromEntityAssembler.class)) {

            mCreate.when(() -> CreateUserCommandFromResourceAssembler.toCommand(any(CreateUserResource.class)))
                    .thenReturn(new Object());

            when(commandService.handle(any())).thenReturn(Optional.of(new Object()));
            mRes.when(() -> UserResourceFromEntityAssembler.toResource(any()))
                    .thenReturn(resource);

            mockMvc.perform(post(BASE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id", is(7)))
                    .andExpect(jsonPath("$.name", is("Bryan")))
                    .andExpect(jsonPath("$.lastname", is("Espejo")))
                    .andExpect(jsonPath("$.email", is("bryan@upc.edu.pe")))
                    .andExpect(jsonPath("$.phonenumber", is("999888777")))
                    .andExpect(jsonPath("$.userId", is(123)))
                    .andExpect(jsonPath("$.profileImage", is("img.png")));
        }
    }

    @Test
    @DisplayName("POST /users -> 400 Bad Request cuando el service no crea")
    void create_badRequest() throws Exception {
        var req = new CreateUserResource("X", "x@x.com", "Y", "1", 1L, "img");

        try (MockedStatic<CreateUserCommandFromResourceAssembler> mCreate =
                     Mockito.mockStatic(CreateUserCommandFromResourceAssembler.class)) {

            mCreate.when(() -> CreateUserCommandFromResourceAssembler.toCommand(any(CreateUserResource.class)))
                    .thenReturn(new Object());

            when(commandService.handle(any())).thenReturn(Optional.empty());

            mockMvc.perform(post(BASE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isBadRequest());
        }
    }

    // ---------- GET /users/{id}/exists ----------
    @Test
    @DisplayName("GET /users/{id}/exists -> 200 OK con boolean")
    void exists_ok() throws Exception {
        when(queryService.existsById(1L)).thenReturn(true);

        mockMvc.perform(get(BASE + "/{id}/exists", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
    }

    // ---------- PUT /users/{id} ----------
    @Test
    @DisplayName("PUT /users/{id} -> 200 OK devuelve UserResource actualizado")
    void update_ok() throws Exception {
        var req = new UpdateUserResource(
                "Bryan New", "bryan.new@upc.edu.pe", "Espejo", "999000111", 123L, "new.png"
        );
        var resource = new UserResource(
                1L, "Bryan New", "Espejo", "bryan.new@upc.edu.pe", "999000111", 123L, "new.png"
        );

        try (MockedStatic<UpdateUserCommandFromResourceAssembler> mUpdate =
                     Mockito.mockStatic(UpdateUserCommandFromResourceAssembler.class);
             MockedStatic<UserResourceFromEntityAssembler> mRes =
                     Mockito.mockStatic(UserResourceFromEntityAssembler.class)) {

            mUpdate.when(() -> UpdateUserCommandFromResourceAssembler.toCommand(eq(1L), any(UpdateUserResource.class)))
                    .thenReturn(new Object());

            when(commandService.handle(any())).thenReturn(Optional.of(new Object()));
            mRes.when(() -> UserResourceFromEntityAssembler.toResource(any()))
                    .thenReturn(resource);

            mockMvc.perform(put(BASE + "/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("Bryan New")))
                    .andExpect(jsonPath("$.email", is("bryan.new@upc.edu.pe")))
                    .andExpect(jsonPath("$.profileImage", is("new.png")));
        }
    }

    @Test
    @DisplayName("PUT /users/{id} -> 404 Not Found cuando el service no actualiza")
    void update_notFound() throws Exception {
        var req = new UpdateUserResource("A","a@a.com","B","0",1L,"img");

        try (MockedStatic<UpdateUserCommandFromResourceAssembler> mUpdate =
                     Mockito.mockStatic(UpdateUserCommandFromResourceAssembler.class)) {

            mUpdate.when(() -> UpdateUserCommandFromResourceAssembler.toCommand(eq(1L), any(UpdateUserResource.class)))
                    .thenReturn(new Object());

            when(commandService.handle(any())).thenReturn(Optional.empty());

            mockMvc.perform(put(BASE + "/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isNotFound());
        }
    }

    // ---------- GET /users/email/{email} ----------
    @Test
    @DisplayName("GET /users/email/{email} -> 200 OK devuelve UserResource")
    void getByEmail_ok() throws Exception {
        var resource = new UserResource(
                5L, "Bryan", "Espejo", "bryan@upc.edu.pe", "999888777", 123L, "img.png"
        );

        when(queryService.handle(any())).thenReturn(Optional.of(new Object()));

        try (MockedStatic<UserResourceFromEntityAssembler> mRes =
                     Mockito.mockStatic(UserResourceFromEntityAssembler.class)) {

            mRes.when(() -> UserResourceFromEntityAssembler.toResource(any()))
                    .thenReturn(resource);

            mockMvc.perform(get(BASE + "/email/{email}", "bryan@upc.edu.pe"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(5)))
                    .andExpect(jsonPath("$.email", is("bryan@upc.edu.pe")));
        }
    }

    @Test
    @DisplayName("GET /users/email/{email} -> 404 Not Found cuando no existe")
    void getByEmail_notFound() throws Exception {
        when(queryService.handle(any())).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE + "/email/{email}", "no@no.com"))
                .andExpect(status().isNotFound());
    }

    // ---------- DELETE /users/{id} ----------
    @Test
    @DisplayName("DELETE /users/{id} -> 204 No Content")
    void delete_noContent() throws Exception {
        Mockito.doNothing().when(commandService).handle(any(DeleteUserCommand.class));

        mockMvc.perform(delete(BASE + "/{id}", 9))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /users/{id} -> 400 Bad Request cuando el service lanza excepción")
    void delete_badRequest() throws Exception {
        Mockito.doThrow(new RuntimeException("cannot delete"))
                .when(commandService).handle(any(DeleteUserCommand.class));

        mockMvc.perform(delete(BASE + "/{id}", 9))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("cannot delete")));
    }
}
*/