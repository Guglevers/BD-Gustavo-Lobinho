package ads.bcd.dto;

import lombok.Data;

@Data
public class DadosInsigniaFeitaDTO {
    private String nomeInsignia;
    private String nomeDesafio;

    public DadosInsigniaFeitaDTO(String nomeInsignia, String nomeDesafio) {
        this.nomeInsignia = nomeInsignia;
        this.nomeDesafio = nomeDesafio;
    }
}
