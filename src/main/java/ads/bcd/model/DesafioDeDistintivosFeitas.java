package ads.bcd.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "desafio_de_distintivos_feitas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DesafioDeDistintivosFeitasID.class)
public class DesafioDeDistintivosFeitas {
    @Id
    @Column(name = "id_distintivo")
    private Integer idDistintivo;

    @Id
    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @ManyToOne
    @JoinColumn(name = "id_distintivo", referencedColumnName = "id_distintivo", insertable = false, updatable = false)
    private Distintivo distintivo;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "data_inicio")
    private Date dataInicio;
}