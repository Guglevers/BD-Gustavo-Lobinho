package ads.bcd.service;

import ads.bcd.dto.DadosEspecialidadeFeitaDTO;
import ads.bcd.dto.DadosInsigniaFeitaDTO;
import ads.bcd.dto.DadosPessoaDTO;
import ads.bcd.repository.PessoaRepository;
import ads.bcd.repository.DesafioDeEspecialidadeFeitaRepository;
import ads.bcd.repository.DesafioDeInsigniaFeitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DesafioDeEspecialidadeFeitaRepository desafioEspecialidadeFeitaRepo;

    @Autowired
    private DesafioDeInsigniaFeitaRepository desafioInsigniaFeitaRepo;

    public Optional<DadosPessoaDTO> dadosBiograficos(Integer idPessoa) {
        return pessoaRepository.buscarDadosPessoa(idPessoa);
    }

    public List<DadosPessoaDTO> jovensPorEspecialidade(Integer idEspecialidade) {
        return pessoaRepository.findPessoasByEspecialidade(idEspecialidade);
    }

    public List<DadosEspecialidadeFeitaDTO> especialidadesPorJovem(Integer idPessoa) {
        return desafioEspecialidadeFeitaRepo.findEspecialidadesByPessoa(idPessoa);
    }

    public List<DadosInsigniaFeitaDTO> insigniasPorJovem(Integer idPessoa) {
        return desafioInsigniaFeitaRepo.findInsigniaByPessoa(idPessoa);
    }

    public List<DadosEspecialidadeFeitaDTO> requisitosCumpridosParaEspecialidade(Integer idPessoa, Integer idEspecialidade) {
        return desafioEspecialidadeFeitaRepo.findRequisitosCumpridos(idPessoa, idEspecialidade);
    }

    public List<DadosPessoaDTO> jovensComTodosRequisitosCruzeiroDoSul() {
        return pessoaRepository.findJovensComTodosRequisitosCruzeiroDoSul();
    }
}