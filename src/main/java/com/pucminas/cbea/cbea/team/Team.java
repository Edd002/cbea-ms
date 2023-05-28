package com.pucminas.cbea.cbea.team;

import com.pucminas.cbea.cbea.coach.Coach;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.swimmer.Swimmer;
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
@Table(name = "t_team")
@SQLDelete(sql = "UPDATE t_team SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Team extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_TEAM")
    @SequenceGenerator(name = "SQ_TEAM", sequenceName = "SQ_TEAM", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "foundation_date", nullable = false)
    private Date foundationDate;

    @Column(name = "titles_number", nullable = false)
    private Integer titlesNumber;

    @Column(name = "championships_number", nullable = false)
    private Integer championshipsNumber;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Swimmer> swimmers;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<ProofTeam> proofTeams;
}