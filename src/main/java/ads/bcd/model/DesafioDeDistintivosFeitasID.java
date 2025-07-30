package ads.bcd.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesafioDeDistintivosFeitasID implements Serializable{
    private Integer idDistintivo;
    private Integer idPessoa;
}