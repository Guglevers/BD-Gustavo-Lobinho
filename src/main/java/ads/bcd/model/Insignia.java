package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "insignia")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insignia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insignia")
    private Integer idInsignia;

    @Column(name = "nome")
    private String nome;
}