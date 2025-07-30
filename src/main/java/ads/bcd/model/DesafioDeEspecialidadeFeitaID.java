package ads.bcd.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesafioDeEspecialidadeFeitaID implements Serializable{
    private Integer idDesafiosEspecialidade;
    private Integer idPessoa;
}