package com.upc.pre.peaceapp.user.infrastructure.persistence.jpa;

import com.upc.pre.peaceapp.user.domain.model.aggregates.MunicipalityProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipalityProfileRepository extends JpaRepository<MunicipalityProfile, Long> {
    Optional<MunicipalityProfile> findByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsByInstitutionalEmail(String institutionalEmail);
    boolean existsByPhone(String phone);
}
