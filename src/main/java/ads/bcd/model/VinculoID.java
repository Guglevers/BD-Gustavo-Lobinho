package ads.bcd.model;

import java.io.Serializable;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VinculoID implements Serializable {
    private Integer idPessoa;
    private Integer idResponsavel;
}