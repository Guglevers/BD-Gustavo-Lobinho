package ads.bcd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ads.bcd.dto.DadosInsigniaFeitaDTO;
import ads.bcd.model.DesafioDeInsigniaFeita;
import ads.bcd.model.DesafioDeInsigniaFeitaID;

public interface DesafioDeInsigniaFeitaRepository extends JpaRepository<DesafioDeInsigniaFeita, DesafioDeInsigniaFeitaID> {
    @Query("""
        SELECT new ads.bcd.dto.DadosInsigniaFeitaDTO(
            d.desafio.insignia.nome,
            d.desafio.nome
        )
        FROM DesafioDeInsigniaFeita d
        WHERE d.idPessoa = :idPessoa
    """)
    List<DadosInsigniaFeitaDTO> findInsigniaByPessoa(@Param("idPessoa") Integer idPessoa);
}
