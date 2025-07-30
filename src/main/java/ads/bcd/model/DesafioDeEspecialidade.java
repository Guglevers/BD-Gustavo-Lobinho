package ads.bcd.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "desafio_especialidade")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesafioDeEspecialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desafios_especialidade")
    private Integer idDesafiosEspecialidade;

    @ManyToOne
    @JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
    private Especialidade especialidade;

    @Column(name = "descricao")
    private String descricao;
}