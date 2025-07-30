package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "distintivo")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Distintivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distintivo")
    private Integer idDistintivo;

    @Column(name = "nome")
    private String nome;
}