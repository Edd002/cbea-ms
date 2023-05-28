package com.pucminas.cbea.cbea.swimmingstyle;

import com.pucminas.cbea.cbea.proofswimmingstyle.ProofSwimmingStyle;
import com.pucminas.cbea.global.entity.AuditEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_swimming_style")
@SQLDelete(sql = "UPDATE t_swimming_style SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id", callSuper = false)
public class SwimmingStyle extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "SQ_SWIMMING_STYLE")
    @SequenceGenerator(name = "SQ_SWIMMING_STYLE", sequenceName = "SQ_SWIMMING_STYLE", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "swimmingStyle", cascade = CascadeType.ALL)
    private List<ProofSwimmingStyle> proofSwimmingStyles;
}