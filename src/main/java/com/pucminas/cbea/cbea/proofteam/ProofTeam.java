package com.pucminas.cbea.cbea.proofteam;

import com.pucminas.cbea.cbea.proof.Proof;
import com.pucminas.cbea.cbea.team.Team;
import com.pucminas.cbea.global.entity.AuditEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_proof_team", uniqueConstraints = {
        @UniqueConstraint(name = "uk_proof_and_team", columnNames = {"proof_id", "team_id"})
})
@SQLDelete(sql = "UPDATE t_proof_team SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class ProofTeam extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_PROOF_TEAM")
    @SequenceGenerator(name = "SQ_PROOF_TEAM", sequenceName = "SQ_PROOF_TEAM", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proof_id", nullable = false)
    private Proof proof;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
