package com.pucminas.cbea.cbea.coach;

import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.entity.AuditEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_coach")
@SQLDelete(sql = "UPDATE t_coach SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Coach extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_COACH")
    @SequenceGenerator(name = "SQ_COACH", sequenceName = "SQ_COACH", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "cellPhone", nullable = false)
    private String cellPhone;

    @Column(name = "landline", nullable = false)
    private String landline;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "professional_formation_description", nullable = false)
    private String professionalFormationDescription;

    @Column(name = "titles_number", nullable = false)
    private Integer titlesNumber;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Team> teams;
}