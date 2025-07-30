package ads.bcd.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "desafio_de_especialidade_feita")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DesafioDeEspecialidadeFeitaID.class)
public class DesafioDeEspecialidadeFeita {

    @Id
    @Column(name = "id_desafios_especialidade")
    private Integer idDesafiosEspecialidade;

    @Id
    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @ManyToOne
    @JoinColumn(name = "id_desafios_especialidade", referencedColumnName = "id_desafios_especialidade", insertable = false, updatable = false)
    private DesafioDeEspecialidade desafio;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    @Column(name = "data")
    private Date data;
}