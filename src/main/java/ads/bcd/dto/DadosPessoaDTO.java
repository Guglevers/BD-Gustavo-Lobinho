package ads.bcd.dto;

import java.util.Date;

import lombok.Data;

    @Data
    public class DadosPessoaDTO {
        private String nome;
        private String telefone;
        private String email;
        private String tipoSanguineo;
        private Date dataNascimento;

    public DadosPessoaDTO(String nome, Date dataNascimento, String telefone, String email, String tipoSanguineo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.tipoSanguineo = tipoSanguineo;
    }
}