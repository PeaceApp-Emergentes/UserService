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
@Table(name = "users")
public class UserProfile extends AuditableAbstractAggregateRoot<UserProfile> {
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String lastname;
    @Column(nullable = false, length = 20)
    private String phonenumber;
    @Column(nullable = false, unique = true, length = 50)
    private String userId;
    @Column(name = "profile_image", length = 255)
    private String profileImage;

    public UserProfile(String name, String email, String lastname,
                       String phonenumber, String userId, String profileImage) {
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.userId = userId;
        this.profileImage = profileImage;
    }
}
