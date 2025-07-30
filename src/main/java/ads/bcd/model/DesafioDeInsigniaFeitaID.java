package ads.bcd.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesafioDeInsigniaFeitaID implements Serializable {
    private Integer idInsignia;
    private Integer idPessoa;
}