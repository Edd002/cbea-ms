package com.pucminas.cbea.cbea.proof;

import com.pucminas.cbea.cbea.championship.Championship;
import com.pucminas.cbea.cbea.proofswimmingstyle.ProofSwimmingStyle;
import com.pucminas.cbea.cbea.proofteam.ProofTeam;
import com.pucminas.cbea.cbea.record.Record;
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
@Table(name = "t_proof")
@SQLDelete(sql = "UPDATE t_proof SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Proof extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_PROOF")
    @SequenceGenerator(name = "SQ_PROOF", sequenceName = "SQ_PROOF", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "realization_date", nullable = false)
    private Date realizationDate;

    @Column(name = "realization_time", nullable = false)
    private Long realizationTime;

    @Column(name = "realization_distance", nullable = false)
    private Long realizationDistance;

    @ManyToOne
    @JoinColumn(name = "championship_id", nullable = false)
    private Championship championship;

    @OneToMany(mappedBy = "proof", cascade = CascadeType.ALL)
    private List<ProofSwimmingStyle> proofSwimmingStyles;

    @OneToMany(mappedBy = "proof", cascade = CascadeType.ALL)
    private List<ProofTeam> proofTeams;

    @OneToMany(mappedBy = "proof", cascade = CascadeType.ALL)
    private List<Record> records;
}