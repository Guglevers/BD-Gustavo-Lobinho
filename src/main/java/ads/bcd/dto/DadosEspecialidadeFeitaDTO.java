package ads.bcd.dto;

import lombok.Data;

@Data
public class DadosEspecialidadeFeitaDTO {
    private String nome;

    public DadosEspecialidadeFeitaDTO(String nome) {
        this.nome = nome;
    }
}
