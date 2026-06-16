package com.upc.pre.peaceapp.user.application.internal.commandservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.MunicipalityProfile;
import com.upc.pre.peaceapp.user.domain.model.commands.CreateMunicipalityProfileCommand;
import com.upc.pre.peaceapp.user.domain.model.commands.UpdateMunicipalityProfileCommand;
import com.upc.pre.peaceapp.user.domain.services.MunicipalityProfileCommandService;
import com.upc.pre.peaceapp.user.infrastructure.persistence.jpa.MunicipalityProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class MunicipalityProfileCommandServiceImpl implements MunicipalityProfileCommandService {
    private final MunicipalityProfileRepository repository;

    public MunicipalityProfileCommandServiceImpl(MunicipalityProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MunicipalityProfile> handle(CreateMunicipalityProfileCommand command) {
        validateCreate(command);
        var normalizedPhone = normalizePeruMunicipalityPhone(command.phone());
        var profile = new MunicipalityProfile(
                command.municipalityName(),
                command.city(),
                command.district(),
                command.institutionalEmail(),
                normalizedPhone,
                command.userId(),
                command.profileImage()
        );
        return Optional.of(repository.save(profile));
    }

    @Override
    public Optional<MunicipalityProfile> handle(UpdateMunicipalityProfileCommand command) {
        var profile = repository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Municipality profile not found"));

        if (!profile.getUserId().equals(command.userId()) && repository.existsByUserId(command.userId())) {
            throw new IllegalArgumentException("User already has a municipality profile");
        }
        if (!profile.getInstitutionalEmail().equals(command.institutionalEmail()) && repository.existsByInstitutionalEmail(command.institutionalEmail())) {
            throw new IllegalArgumentException("Institutional email is already in use");
        }
        var normalizedPhone = normalizePeruMunicipalityPhone(command.phone());
        if (!profile.getPhone().equals(normalizedPhone) && repository.existsByPhone(normalizedPhone)) {
            throw new IllegalArgumentException("Phone is already in use");
        }

        profile.setMunicipalityName(command.municipalityName());
        profile.setCity(command.city());
        profile.setDistrict(command.district());
        profile.setInstitutionalEmail(command.institutionalEmail());
        profile.setPhone(normalizedPhone);
        profile.setUserId(command.userId());
        profile.setProfileImage(command.profileImage());
        return Optional.of(repository.save(profile));
    }

    private void validateCreate(CreateMunicipalityProfileCommand command) {
        if (isBlank(command.municipalityName()) || isBlank(command.city()) || isBlank(command.district())
                || isBlank(command.institutionalEmail()) || isBlank(command.phone()) || isBlank(command.userId())) {
            throw new IllegalArgumentException("Missing required municipality profile data");
        }
        var normalizedPhone = normalizePeruMunicipalityPhone(command.phone());
        if (!command.institutionalEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid institutional email");
        }
        if (repository.existsByUserId(command.userId())) {
            throw new IllegalArgumentException("User already has a municipality profile");
        }
        if (repository.existsByInstitutionalEmail(command.institutionalEmail())) {
            throw new IllegalArgumentException("Institutional email is already in use");
        }
        if (repository.existsByPhone(normalizedPhone)) {
            throw new IllegalArgumentException("Phone is already in use");
        }
    }

    private String normalizePeruMunicipalityPhone(String rawPhone) {
        if (isBlank(rawPhone)) {
            throw new IllegalArgumentException("Phone is required");
        }

        var compactPhone = rawPhone.trim().replaceAll("[\\s\\-()]", "");
        if (compactPhone.matches("9\\d{8}")) {
            return compactPhone;
        }
        if (compactPhone.matches("\\+519\\d{8}")) {
            return compactPhone;
        }
        if (compactPhone.matches("01\\d{7}")) {
            return compactPhone;
        }
        if (compactPhone.matches("\\d{7}")) {
            return "01" + compactPhone;
        }
        throw new IllegalArgumentException("Phone must be a Peru mobile number, +51 mobile number, or Lima/Callao landline");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
