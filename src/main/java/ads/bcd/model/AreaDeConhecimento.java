package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "area_de_conhecimento")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaDeConhecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area_de_conhecimento")
    private Integer idAreaDeConhecimento;

    @Column(name = "nome")
    private String nome;
}