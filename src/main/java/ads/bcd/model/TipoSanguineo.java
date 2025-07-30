package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_sanguineo")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoSanguineo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sanguineo")
    private Integer idTipoSanguineo;

    @Column(name = "tipo")
    private String tipo;
}