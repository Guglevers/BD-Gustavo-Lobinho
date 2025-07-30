package ads.bcd.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ads.bcd.model.DesafioDeDistintivosFeitas;
import ads.bcd.model.DesafioDeEspecialidadeFeita;
import ads.bcd.model.DesafioDeInsigniaFeita;
import ads.bcd.model.Pessoa;
import ads.bcd.repository.DesafioDeDistintivosFeitasRepository;
import ads.bcd.repository.DesafioDeEspecialidadeFeitaRepository;
import ads.bcd.repository.DesafioDeEspecialidadeRepository;
import ads.bcd.repository.DesafioDeInsigniaFeitaRepository;
import ads.bcd.repository.DesafioDeInsigniaRepository;
import ads.bcd.repository.DistintivoRepository;
import ads.bcd.repository.PessoaRepository;
import ads.bcd.repository.TipoSanguineoRepository;
import ads.bcd.service.MenuService.DadosConquistaDistintivo;
import ads.bcd.service.MenuService.DadosConquistaEspecialidade;
import ads.bcd.service.MenuService.DadosConquistaInsignia;
import ads.bcd.service.MenuService.DadosNovoMembro;

/**
 * Serviço responsável pelas operações de banco de dados
 * Gerencia todas as operações CRUD do sistema
 */
@Service
public class DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @Autowired
    private DesafioDeDistintivosFeitasRepository repositorioDistintivosConcluidos;
    @Autowired
    private DesafioDeEspecialidadeRepository repositorioDesafioEspecialidade;
    @Autowired
    private DesafioDeEspecialidadeFeitaRepository repositorioEspecialidadesConcluidas;
    @Autowired
    private DesafioDeInsigniaRepository repositorioDesafioInsignia;
    @Autowired
    private DesafioDeInsigniaFeitaRepository repositorioInsigniasConcluidas;
    @Autowired
    private DistintivoRepository repositorioDistintivo;
    @Autowired
    private PessoaRepository repositorioPessoa;
    @Autowired
    private TipoSanguineoRepository repositorioTipoSanguineo;

    /**
     * Adiciona novo membro ao banco de dados
     */
    public boolean adicionarNovoMembro(DadosNovoMembro dados) {
        try {
            Pessoa novoMembro = new Pessoa();
            novoMembro.setNome(dados.nome);
            novoMembro.setEmail(dados.email);
            novoMembro.setTelefone(dados.telefone);
            
            // Conversão de data
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(dados.dataNascimento);
            novoMembro.setDataNascimento(date);
            
            // Buscar tipo sanguíneo
            novoMembro.setTipoSanguineo(
                repositorioTipoSanguineo.findById(dados.tipoSanguineoId).orElse(null)
            );
            
            repositorioPessoa.save(novoMembro);
            logger.info("Novo membro cadastrado: {}", dados.nome);
            return true;
            
        } catch (Exception e) {
            logger.error("Erro ao cadastrar membro: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Registra conquista de insígnia
     */
    public boolean adicionarConquistaInsignia(DadosConquistaInsignia dados) {
        try {
            DesafioDeInsigniaFeita novaConquista = new DesafioDeInsigniaFeita();
            novaConquista.setIdInsignia(dados.insigniaId);
            novaConquista.setIdPessoa(dados.membroId);
            novaConquista.setDesafio(
                repositorioDesafioInsignia.findById(dados.insigniaId).orElse(null)
            );
            novaConquista.setPessoa(
                repositorioPessoa.findById(dados.membroId).orElse(null)
            );
            
            // Conversão de data
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(dados.dataConquista);
            novaConquista.setData(date);

            repositorioInsigniasConcluidas.save(novaConquista);
            logger.info("Insígnia {} registrada para membro {}", dados.insigniaId, dados.membroId);
            return true;
            
        } catch (Exception e) {
            logger.error("Erro ao registrar insígnia: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Registra conquista de distintivo
     */
    public boolean adicionarConquistaDistintivo(DadosConquistaDistintivo dados) {
        try {
            DesafioDeDistintivosFeitas novaConquistaDistintivo = new DesafioDeDistintivosFeitas();
            
            novaConquistaDistintivo.setIdDistintivo(dados.distintivoId);
            novaConquistaDistintivo.setIdPessoa(dados.membroId);
            novaConquistaDistintivo.setDistintivo(
                repositorioDistintivo.findById(dados.distintivoId).orElse(null)
            );
            novaConquistaDistintivo.setPessoa(
                repositorioPessoa.findById(dados.membroId).orElse(null)
            );
            
            // Conversão de datas SQL
            java.sql.Date dataInicial = java.sql.Date.valueOf(dados.dataInicio);
            java.sql.Date dataFinal = java.sql.Date.valueOf(dados.dataFim);
            novaConquistaDistintivo.setDataInicio(dataInicial);
            novaConquistaDistintivo.setDataFim(dataFinal);

            repositorioDistintivosConcluidos.save(novaConquistaDistintivo);
            logger.info("Distintivo {} registrado para membro {}", dados.distintivoId, dados.membroId);
            return true;
            
        } catch (Exception e) {
            logger.error("Erro ao registrar distintivo: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Registra conquista de especialidade
     */
    public boolean adicionarConquistaEspecialidade(DadosConquistaEspecialidade dados) {
        try {
            DesafioDeEspecialidadeFeita novaEspecialidadeFeita = new DesafioDeEspecialidadeFeita();
            
            novaEspecialidadeFeita.setIdDesafiosEspecialidade(dados.especialidadeId);
            novaEspecialidadeFeita.setIdPessoa(dados.membroId);
            novaEspecialidadeFeita.setDesafio(
                repositorioDesafioEspecialidade.findById(dados.especialidadeId).orElse(null)
            );
            novaEspecialidadeFeita.setPessoa(
                repositorioPessoa.findById(dados.membroId).orElse(null)
            );
            
            // Conversão de data
            LocalDate dataLocal = LocalDate.parse(dados.dataConquista);
            Date dataObjeto = Date.from(
                dataLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );
            novaEspecialidadeFeita.setData(dataObjeto);

            repositorioEspecialidadesConcluidas.save(novaEspecialidadeFeita);
            logger.info("Especialidade {} registrada para membro {}", dados.especialidadeId, dados.membroId);
            return true;
            
        } catch (Exception e) {
            logger.error("Erro ao registrar especialidade: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verifica se um membro existe no banco de dados
     */
    public boolean membroExiste(int codigoMembro) {
        return repositorioPessoa.existsById(codigoMembro);
    }

    /**
     * Verifica se uma insígnia existe no banco de dados
     */
    public boolean insigniaExiste(int codigoInsignia) {
        return repositorioDesafioInsignia.existsById(codigoInsignia);
    }

    /**
     * Verifica se um distintivo existe no banco de dados
     */
    public boolean distintivoExiste(int codigoDistintivo) {
        return repositorioDistintivo.existsById(codigoDistintivo);
    }

    /**
     * Verifica se uma especialidade existe no banco de dados
     */
    public boolean especialidadeExiste(int codigoEspecialidade) {
        return repositorioDesafioEspecialidade.existsById(codigoEspecialidade);
    }

    /**
     * Verifica se um tipo sanguíneo existe no banco de dados
     */
    public boolean tipoSanguineoExiste(int codigoTipoSanguineo) {
        return repositorioTipoSanguineo.existsById(codigoTipoSanguineo);
    }
}