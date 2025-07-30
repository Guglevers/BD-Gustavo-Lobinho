package ads.bcd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ads.bcd.service.DatabaseService;
import ads.bcd.service.MenuService;
import ads.bcd.service.RelatorioService;

@SpringBootApplication
public class ExemploJpaApplication {

    private static final Logger logger = LoggerFactory.getLogger(ExemploJpaApplication.class);
    
    @Autowired
    private MenuService servicoMenu;
    
    @Autowired
    private DatabaseService servicoBancoDados;
    
    @Autowired
    private RelatorioService servicoRelatorio;

    public static void main(String[] args) {
        SpringApplication.run(ExemploJpaApplication.class, args);
        logger.info("Sistema finalizado com sucesso");
    }

    /**
     * Método principal de execução da aplicação
     */
    @Bean
    public CommandLineRunner demo() {
        return (argumentos) -> {
            try {
                logger.info("Inicializando Sistema de Gestão Escoteiro...");
                
                boolean sistemaAtivo = true;
                
                while (sistemaAtivo) {
                    servicoMenu.mostrarMenuPrincipal();
                    int opcaoSelecionada = servicoMenu.lerOpcaoMenu();
                    
                    switch (opcaoSelecionada) {
                        case 1 -> consultarDadosBiograficos();
                        case 2 -> listarMembrosPorEspecialidade();
                        case 3 -> exibirConquistasIndividuais();
                        case 4 -> verificarProgressoEspecialidade();
                        case 5 -> listarPortadoresCruzeiroSul();
                        case 6 -> cadastrarNovoMembro();
                        case 7 -> registrarConquistaInsignia();
                        case 8 -> registrarConquistaDistintivo();
                        case 9 -> registrarConquistaEspecialidade();
                        case 0 -> {
                            servicoMenu.exibirMensagemEncerramento();
                            servicoMenu.fecharScanner();
                            sistemaAtivo = false;
                        }
                        default -> servicoMenu.exibirMensagemErro("Opção inválida! Tente novamente.");
                    }
                }
            } catch (Exception e) {
                logger.error("Erro durante execução: " + e.toString());
            }
        };
    }

    /**
     * Consulta dados biográficos de um membro
     */
    private void consultarDadosBiograficos() {
        int codigoPessoa = servicoMenu.lerCodigoMembro();
        servicoRelatorio.dadosBiograficos(codigoPessoa).ifPresent(dado -> {
            servicoMenu.exibeDadosBiograficos(dado);
        });
    }

    /**
     * Lista membros por especialidade
     */
    private void listarMembrosPorEspecialidade() {
        int codigoEspecialidade = servicoMenu.lerCodigoEspecialidade();
        servicoMenu.exibirCabecalho("MEMBROS POR ESPECIALIDADE");
        servicoRelatorio.jovensPorEspecialidade(codigoEspecialidade)
            .forEach(item -> {                
                servicoMenu.exibeDadosBiograficos(item);
            });
        }
    /**
     * Exibe conquistas individuais de um membro
     */
    private void exibirConquistasIndividuais() {
        int codigoJovem = servicoMenu.lerCodigoMembro();
        servicoMenu.exibirCabecalho("CONQUISTAS DO MEMBRO");

        servicoMenu.exibirEspecialidadesConquistadas(
            servicoRelatorio.especialidadesPorJovem(codigoJovem)
        );

        servicoMenu.exibirInsigniasConquistadas(
            servicoRelatorio.insigniasPorJovem(codigoJovem)
        );
    }

    /**
     * Verifica progresso em especialidade específica
     */
    private void verificarProgressoEspecialidade() {
        int codigoJovem = servicoMenu.lerCodigoMembro();
        int codigoEsp = servicoMenu.lerCodigoEspecialidade();
        
        servicoMenu.exibirCabecalho("PROGRESSO EM ESPECIALIDADE");
        servicoRelatorio.requisitosCumpridosParaEspecialidade(codigoJovem, codigoEsp)
            .forEach(requisito -> System.out.println(requisito.getNome() + "\n"));
    }

    /**
     * Lista portadores do Cruzeiro do Sul
     */
    private void listarPortadoresCruzeiroSul() {
        servicoMenu.exibirCabecalho("PORTADORES DO CRUZEIRO DO SUL");
        servicoRelatorio.jovensComTodosRequisitosCruzeiroDoSul()
            .forEach(membro -> System.out.println(membro.getNome() + "\n"));
    }

    /**
     * Cadastra novo membro
     */
    private void cadastrarNovoMembro() {
        try {
            MenuService.DadosNovoMembro dados = servicoMenu.coletarDadosNovoMembro();
            
            if (!servicoBancoDados.tipoSanguineoExiste(dados.tipoSanguineoId)) {
                servicoMenu.exibirMensagemErro("Tipo sanguíneo não encontrado!");
                return;
            }
            
            if (servicoBancoDados.adicionarNovoMembro(dados)) {
                servicoMenu.exibirMensagemSucesso("Membro cadastrado com sucesso no sistema!");
            } else {
                servicoMenu.exibirMensagemErro("Erro ao cadastrar membro. Verifique os dados informados.");
            }
        } catch (Exception e) {
            servicoMenu.exibirMensagemErro("Erro no formato dos dados. Verifique as informações inseridas.");
            logger.error("Erro ao cadastrar membro: {}", e.getMessage());
        }
    }

    /**
     * Registra conquista de insígnia
     */
    private void registrarConquistaInsignia() {
        try {
            MenuService.DadosConquistaInsignia dados = servicoMenu.coletarDadosConquistaInsignia();
            
            // Validações
            if (!servicoBancoDados.insigniaExiste(dados.insigniaId)) {
                servicoMenu.exibirMensagemErro("Insígnia não encontrada!");
                return;
            }
            
            if (!servicoBancoDados.membroExiste(dados.membroId)) {
                servicoMenu.exibirMensagemErro("Membro não encontrado!");
                return;
            }
            
            if (servicoBancoDados.adicionarConquistaInsignia(dados)) {
                servicoMenu.exibirMensagemSucesso("Insígnia registrada no histórico do membro!");
            } else {
                servicoMenu.exibirMensagemErro("Erro ao registrar insígnia. Verifique os dados informados.");
            }
        } catch (Exception e) {
            servicoMenu.exibirMensagemErro("Erro no formato dos dados. Verifique as informações inseridas.");
            logger.error("Erro ao registrar insígnia: {}", e.getMessage());
        }
    }

    /**
     * Registra conquista de distintivo
     */
    private void registrarConquistaDistintivo() {
        try {
            MenuService.DadosConquistaDistintivo dados = servicoMenu.coletarDadosConquistaDistintivo();
            
            // Validações
            if (!servicoBancoDados.distintivoExiste(dados.distintivoId)) {
                servicoMenu.exibirMensagemErro("Distintivo não encontrado!");
                return;
            }
            
            if (!servicoBancoDados.membroExiste(dados.membroId)) {
                servicoMenu.exibirMensagemErro("Membro não encontrado!");
                return;
            }
            
            if (servicoBancoDados.adicionarConquistaDistintivo(dados)) {
                servicoMenu.exibirMensagemSucesso("Distintivo registrado no histórico do membro!");
            } else {
                servicoMenu.exibirMensagemErro("Erro ao registrar distintivo. Verifique os dados informados.");
            }
        } catch (Exception e) {
            servicoMenu.exibirMensagemErro("Erro no formato dos dados. Verifique as informações inseridas.");
            logger.error("Erro ao registrar distintivo: {}", e.getMessage());
        }
    }

    /**
     * Registra conquista de especialidade
     */
    private void registrarConquistaEspecialidade() {
        try {
            MenuService.DadosConquistaEspecialidade dados = servicoMenu.coletarDadosConquistaEspecialidade();
            
            // Validações
            if (!servicoBancoDados.especialidadeExiste(dados.especialidadeId)) {
                servicoMenu.exibirMensagemErro("Especialidade não encontrada!");
                return;
            }
            
            if (!servicoBancoDados.membroExiste(dados.membroId)) {
                servicoMenu.exibirMensagemErro("Membro não encontrado!");
                return;
            }
            
            if (servicoBancoDados.adicionarConquistaEspecialidade(dados)) {
                servicoMenu.exibirMensagemSucesso("Especialidade registrada no histórico do membro!");
            } else {
                servicoMenu.exibirMensagemErro("Erro ao registrar especialidade. Verifique os dados informados.");
            }
        } catch (Exception e) {
            servicoMenu.exibirMensagemErro("Erro no formato dos dados. Verifique as informações inseridas.");
            logger.error("Erro ao registrar especialidade: {}", e.getMessage());
        }
    }
}