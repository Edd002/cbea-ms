package com.pucminas.cbea.cbea.record;

import com.pucminas.cbea.cbea.proof.Proof;
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
@Table(name = "t_record")
@SQLDelete(sql = "UPDATE t_record SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Record extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_RECORD")
    @SequenceGenerator(name = "SQ_RECORD", sequenceName = "SQ_RECORD", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "distance", nullable = false)
    private Long distance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "realization_date", nullable = false)
    private Date realizationDate;

    @Column(name = "realization_time", nullable = false)
    private Long realizationTime;

    @ManyToOne
    @JoinColumn(name = "proof_id", nullable = false)
    private Proof proof;
}