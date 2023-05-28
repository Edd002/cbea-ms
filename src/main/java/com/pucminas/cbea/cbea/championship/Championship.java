package com.pucminas.cbea.cbea.championship;

import com.pucminas.cbea.cbea.proof.Proof;
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
@Table(name = "t_championship")
@SQLDelete(sql = "UPDATE t_championship SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class Championship extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_CHAMPIONSHIP")
    @SequenceGenerator(name = "SQ_CHAMPIONSHIP", sequenceName = "SQ_CHAMPIONSHIP", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "realization_year", nullable = false)
    private Integer realizationYear;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "initial_date", nullable = false)
    private Date initialDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL)
    private List<Proof> proofs;
}