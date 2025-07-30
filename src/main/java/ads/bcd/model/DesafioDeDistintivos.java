package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "desafio_distintivo")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesafioDeDistintivos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desafio_distintivo")
    private Integer idDesafioDistintivo;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_distintivo", referencedColumnName = "id_distintivo")
    private Distintivo distintivo;
}