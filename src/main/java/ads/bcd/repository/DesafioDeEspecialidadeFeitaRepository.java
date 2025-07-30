package ads.bcd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ads.bcd.dto.DadosEspecialidadeFeitaDTO;
import ads.bcd.model.DesafioDeEspecialidadeFeita;
import ads.bcd.model.DesafioDeEspecialidadeFeitaID;

public interface DesafioDeEspecialidadeFeitaRepository extends JpaRepository<DesafioDeEspecialidadeFeita, DesafioDeEspecialidadeFeitaID> {

    List<DesafioDeEspecialidadeFeita> findByIdPessoa(Integer idPessoa);

    @Query("""
        SELECT new ads.bcd.dto.DadosEspecialidadeFeitaDTO(
            d.desafio.especialidade.nome
        )
        FROM DesafioDeEspecialidadeFeita d
        WHERE d.idPessoa = :idPessoa AND d.desafio.especialidade.idEspecialidade = :idEspecialidade
    """)
    List<DadosEspecialidadeFeitaDTO> findRequisitosCumpridos(@Param("idPessoa") Integer idPessoa, @Param("idEspecialidade") Integer idEspecialidade);

    @Query("""
        SELECT new ads.bcd.dto.DadosEspecialidadeFeitaDTO(
            d.desafio.especialidade.nome
        )
        FROM DesafioDeEspecialidadeFeita d
        WHERE d.idPessoa = :idPessoa
    """)
    List<DadosEspecialidadeFeitaDTO> findEspecialidadesByPessoa(@Param("idPessoa") Integer idPessoa);

}
                                        