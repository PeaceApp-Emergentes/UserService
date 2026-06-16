package com.upc.pre.peaceapp.user.domain.model.aggregates;

import com.upc.pre.peaceapp.shared.documentation.models.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "municipalities")
public class MunicipalityProfile extends AuditableAbstractAggregateRoot<MunicipalityProfile> {
    @Column(nullable = false, length = 150)
    private String municipalityName;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String district;

    @Column(nullable = false, unique = true, length = 150)
    private String institutionalEmail;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Column(nullable = false, unique = true, length = 50)
    private String userId;

    @Column(name = "profile_image", length = 255)
    private String profileImage;

    public MunicipalityProfile(String municipalityName, String city, String district,
                               String institutionalEmail, String phone, String userId,
                               String profileImage) {
        this.municipalityName = municipalityName;
        this.city = city;
        this.district = district;
        this.institutionalEmail = institutionalEmail;
        this.phone = phone;
        this.userId = userId;
        this.profileImage = profileImage;
    }
}
