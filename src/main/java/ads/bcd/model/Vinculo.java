package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vinculo")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(VinculoID.class)
public class Vinculo {
    @Id
    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Id
    @Column(name = "id_responsavel")
    private Integer idResponsavel;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id_responsavel", insertable = false, updatable = false)
    private Responsavel responsavel;
}