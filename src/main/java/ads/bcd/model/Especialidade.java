package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "especialidade")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    private Integer idEspecialidade;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_area_de_conhecimento", referencedColumnName = "id_area_de_conhecimento")
    private AreaDeConhecimento areaDeConhecimento;
}