package eu.planlos.pretixtonextcloudintegrator.pretix.model;

import eu.planlos.pretixtonextcloudintegrator.pretix.model.dto.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public final class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private LocalDateTime expires;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Position> positionList;

    public Booking(@NotNull String code, @NotNull String firstname, @NotNull String lastname, @NotNull String email, @NotNull LocalDateTime expires, @NotNull List<Position> positionList) {
        this.code = code;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.expires = expires;
        this.positionList = positionList;
    }
}