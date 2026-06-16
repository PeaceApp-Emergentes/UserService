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
        return repository.findAll().stream()
                .filter(profile -> normalizeDistrict(profile.getDistrict()).equals(normalizedDistrict))
                .findFirst();
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
