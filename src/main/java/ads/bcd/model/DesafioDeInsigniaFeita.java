package ads.bcd.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "desafio_de_insignia_feita")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DesafioDeInsigniaFeitaID.class)
public class DesafioDeInsigniaFeita {
    @Id
    @Column(name = "id_insignia")
    private Integer idInsignia;

    @Id
    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @ManyToOne
    @JoinColumn(name = "id_insignia", referencedColumnName = "id_desafios_insignia", insertable = false, updatable = false)
    private DesafioDeInsignia desafio;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    @Column(name = "data")
    private Date data;
}