package com.upc.pre.peaceapp.user.application.internal.queryservices;

import com.upc.pre.peaceapp.user.domain.model.aggregates.MunicipalityProfile;
import com.upc.pre.peaceapp.user.domain.model.queries.GetMunicipalityProfileByUserIdQuery;
import com.upc.pre.peaceapp.user.domain.services.MunicipalityProfileQueryService;
import com.upc.pre.peaceapp.user.infrastructure.persistence.jpa.MunicipalityProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
@Slf4j
public class MunicipalityProfileQueryServiceImpl implements MunicipalityProfileQueryService {
    private final MunicipalityProfileRepository repository;

    public MunicipalityProfileQueryServiceImpl(MunicipalityProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MunicipalityProfile> handle(GetMunicipalityProfileByUserIdQuery query) {
        return repository.findByUserId(query.userId());
    }

    @Override
    public Optional<MunicipalityProfile> findByDistrict(String district) {
        var normalizedDistrict = normalizeDistrict(district);
        // Puede haber más de un perfil para el mismo distrito (registros de prueba).
        // Preferir el más reciente que tenga teléfono; si ninguno tiene, el más reciente.
        var matches = repository.findAll().stream()
                .filter(profile -> normalizeDistrict(profile.getDistrict()).equals(normalizedDistrict))
                .sorted(java.util.Comparator.comparing(MunicipalityProfile::getId).reversed())
                .toList();
        return matches.stream()
                .filter(p -> p.getPhone() != null && !p.getPhone().isBlank())
                .findFirst()
                .or(() -> matches.stream().findFirst());
    }

    @Override
    public boolean existsByUserId(String userId) {
        return repository.existsByUserId(userId);
    }

    private String normalizeDistrict(String value) {
        if (value == null) return "";
        return Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace("(cercado)", "")
                .replace("-", " ")
                .replaceAll("\\s+", " ")
                .trim()
                .toLowerCase();
    }
}
