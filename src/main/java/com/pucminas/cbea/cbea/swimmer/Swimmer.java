package com.pucminas.cbea.cbea.swimmer;

import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.entity.AuditEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_swimmer")
@SQLDelete(sql = "UPDATE t_swimmer SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Swimmer extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_SWIMMER")
    @SequenceGenerator(name = "SQ_SWIMMER", sequenceName = "SQ_SWIMMER", schema = "public", allocationSize = 1)
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

    @Column(name = "number_bronze_medals", nullable = false)
    private Integer numberBronzeMedals;

    @Column(name = "number_silver_medals", nullable = false)
    private Integer numberSilverMedals;

    @Column(name = "number_gold_medals", nullable = false)
    private Integer numberGoldMedals;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}