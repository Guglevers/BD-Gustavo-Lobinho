package ads.bcd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "desafio_insignia")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesafioDeInsignia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desafios_insignia")
    private Integer idDesafiosInsignia;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_insignia", referencedColumnName = "id_insignia")
    private Insignia insignia;
}