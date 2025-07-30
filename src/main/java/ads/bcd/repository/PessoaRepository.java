package ads.bcd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ads.bcd.dto.DadosPessoaDTO;
import ads.bcd.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("""
        SELECT new ads.bcd.dto.DadosPessoaDTO(
            p.nome,
            p.dataNascimento,
            p.telefone,
            p.email,
            p.tipoSanguineo.tipo
        )
        FROM Pessoa p
        WHERE p.idPessoa = :idPessoa
    """)
    Optional<DadosPessoaDTO> buscarDadosPessoa(@Param("idPessoa") Integer idPessoa);

    @Query("""
        SELECT new ads.bcd.dto.DadosPessoaDTO(
            p.nome,
            p.dataNascimento,
            p.telefone,
            p.email,
            p.tipoSanguineo.tipo
        )
        FROM Pessoa p
        WHERE p.idPessoa IN (
            SELECT d.idPessoa
            FROM DesafioDeEspecialidadeFeita d
            WHERE d.desafio.especialidade.idEspecialidade = :idEspecialidade
        )
    """)
    List<DadosPessoaDTO> findPessoasByEspecialidade(@Param("idEspecialidade") Integer idEspecialidade);

    @Query("""
        SELECT new ads.bcd.dto.DadosPessoaDTO(
            p.nome,
            p.dataNascimento,
            p.telefone,
            p.email,
            p.tipoSanguineo.tipo
        )
        FROM Pessoa p
        WHERE p.idPessoa IN (
            SELECT ddf.idPessoa
            FROM DesafioDeDistintivosFeitas ddf
            JOIN ddf.distintivo dt
            WHERE dt.nome = 'Cruzeiro do Sul'
        )
    """)
    List<DadosPessoaDTO> findJovensComTodosRequisitosCruzeiroDoSul();
}