package ads.bcd.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ads.bcd.dto.DadosEspecialidadeFeitaDTO;
import ads.bcd.dto.DadosInsigniaFeitaDTO;
import ads.bcd.dto.DadosPessoaDTO;

/**
 * Serviço responsável pela interface do usuário
 * Gerencia todos os aspectos de exibição no terminal
 */
@Service
public class MenuService {
    
    private final Scanner entrada = new Scanner(System.in);

    /**
     * Exibe o menu principal do sistema
     */
    public void mostrarMenuPrincipal() {
        System.out.println("\n" + "/".repeat(60));
        System.out.println("           SISTEMA DE GESTÃO DO GRUPO ESCOTEIRO");
        System.out.println("/".repeat(60));
        System.out.println("│ [1] Consultar informações biográficas do membro        │");
        System.out.println("│ [2] Listar membros por área de especialidade           │");
        System.out.println("│ [3] Exibir conquistas individuais do membro            │");
        System.out.println("│ [4] Verificar progresso em especialidade específica    │");
        System.out.println("│ [5] Listar portadores do Cruzeiro do Sul               │");
        System.out.println("│ [6] Incluir novo membro no grupo                       │");
        System.out.println("│ [7] Registrar conquista de insígnia                    │");
        System.out.println("│ [8] Registrar conquista de distintivo                  │");
        System.out.println("│ [9] Registrar conquista de especialidade               │");
        System.out.println("│ [0] Encerrar sistema                                   │");
        System.out.println("/".repeat(60));
        System.out.print("Selecione uma opção: ");
    }

    /**
     * Exibe cabeçalho para seções do sistema
     */
    public void exibirCabecalho(String titulo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(50));
    }

    /**
     * Exibe mensagem de sucesso
     */
    public void exibirMensagemSucesso(String mensagem) {
        System.out.println("\nSUCESSO: " + mensagem);
    }

    /**
     * Exibe mensagem de erro
     */
    public void exibirMensagemErro(String mensagem) {
        System.out.println("\nERRO: " + mensagem);
    }

    public void exibeDadosBiograficos(DadosPessoaDTO dado){
        System.out.println("========================");
        System.out.println("Nome: " + dado.getNome());
        System.out.println("Nascimento: " + dado.getDataNascimento());
        System.out.println("Telefone: " + dado.getTelefone());
        System.out.println("Email: " + dado.getEmail());
        System.out.println("Tipo sanguíneo: " + dado.getTipoSanguineo());
        System.out.println("========================");
    }

    public void exibirEspecialidadesConquistadas(List<DadosEspecialidadeFeitaDTO> especialidades) {
        System.out.println("\nESPECIALIDADES CONQUISTADAS:");
        especialidades.forEach(item -> System.out.println(
            "Nome: " + item.getNome() + "\n"
        ));
    }

    public void exibirInsigniasConquistadas(List<DadosInsigniaFeitaDTO> insignias) {
        System.out.println("\nINSÍGNIAS CONQUISTADAS:");
        insignias.forEach(item -> System.out.println(
            "Nome: " + item.getNomeInsignia() + "\n" +
            "Descrição: " + item.getNomeDesafio() + "\n"
        ));
    }

    /**
     * Lê opção do menu com validação
     */
    public int lerOpcaoMenu() {
        while (true) {
            if (entrada.hasNextInt()) {
                int opcao = entrada.nextInt();
                entrada.nextLine(); // Consumir a quebra de linha restante
                return opcao;
            } else {
                System.out.print("Entrada inválida\nDigite: ");
                entrada.next();
            }
        }
    }

    /**
     * Coleta dados para cadastro de novo membro
     */
    public DadosNovoMembro coletarDadosNovoMembro() {
        exibirCabecalho("CADASTRO DE NOVO MEMBRO");
        
        System.out.print("Digite o nome completo: ");
        String nomeCompleto = entrada.nextLine();
        
        System.out.print("Informe o e-mail: ");
        String enderecoEmail = entrada.nextLine();
        
        System.out.print("Número de telefone: ");
        String numeroTelefone = entrada.nextLine();
        
        System.out.print("Data de nascimento (formato: YYYY-MM-DD): ");
        String nascimento = entrada.nextLine();
        
        System.out.print("ID do tipo sanguíneo: ");
        int codigoTipoSanguineo = entrada.nextInt();
        entrada.nextLine(); // Consumir quebra de linha

        return new DadosNovoMembro(nomeCompleto, enderecoEmail, numeroTelefone, nascimento, codigoTipoSanguineo);
    }

    /**
     * Coleta dados para registro de insígnia
     */
    public DadosConquistaInsignia coletarDadosConquistaInsignia() {
        exibirCabecalho("REGISTRO DE INSÍGNIA CONQUISTADA");
        
        System.out.print("Código da insígnia: ");
        int codigoInsignia = entrada.nextInt();
        
        System.out.print("Código do membro: ");
        int codigoMembro = entrada.nextInt();
        
        System.out.print("Data da conquista (YYYY-MM-DD): ");
        String dataConquista = entrada.next();
        entrada.nextLine(); // Consumir quebra de linha restante

        return new DadosConquistaInsignia(codigoInsignia, codigoMembro, dataConquista);
    }

    /**
     * Coleta dados para registro de distintivo
     */
    public DadosConquistaDistintivo coletarDadosConquistaDistintivo() {
        exibirCabecalho("REGISTRO DE DISTINTIVO CONQUISTADO");
        
        System.out.print("Código do distintivo: ");
        int codigoDistintivo = entrada.nextInt();
        
        System.out.print("Código do membro: ");
        int codigoMembro = entrada.nextInt();
        
        System.out.print("Data de início (YYYY-MM-DD): ");
        String inicioAtividade = entrada.next();
        
        System.out.print("Data de conclusão (YYYY-MM-DD): ");
        String fimAtividade = entrada.next();
        entrada.nextLine(); // Consumir quebra de linha restante

        return new DadosConquistaDistintivo(codigoDistintivo, codigoMembro, inicioAtividade, fimAtividade);
    }

    /**
     * Coleta dados para registro de especialidade
     */
    public DadosConquistaEspecialidade coletarDadosConquistaEspecialidade() {
        exibirCabecalho("REGISTRO DE ESPECIALIDADE CONQUISTADA");
        
        System.out.print("Código da especialidade: ");
        int codigoEspecialidade = entrada.nextInt();
        
        System.out.print("Código do membro: ");
        int codigoMembro = entrada.nextInt();
        
        System.out.print("Data da conquista (YYYY-MM-DD): ");
        String dataConquista = entrada.next();
        entrada.nextLine(); // Consumir quebra de linha restante

        return new DadosConquistaEspecialidade(codigoEspecialidade, codigoMembro, dataConquista);
    }

    /**
     * Lê código do membro
     */
    public int lerCodigoMembro() {
        System.out.print("Digite o código do membro: ");
        int codigo = entrada.nextInt();
        entrada.nextLine(); // Consumir a quebra de linha restante
        return codigo;
    }

    /**
     * Lê código da especialidade
     */
    public int lerCodigoEspecialidade() {
        System.out.print("Digite o código da especialidade: ");
        int codigo = entrada.nextInt();
        entrada.nextLine(); // Consumir a quebra de linha restante
        return codigo;
    }

    /**
     * Exibe mensagem de encerramento do sistema
     */
    public void exibirMensagemEncerramento() {
        System.out.println("\n" + "═".repeat(40));
        System.out.println("  Sistema encerrado com sucesso!");
        System.out.println("  Obrigado por usar nosso sistema.");
        System.out.println("═".repeat(40));
    }

    /**
     * Fecha o scanner
     */
    public void fecharScanner() {
        entrada.close();
    }

    // Classes DTOs para transporte de dados
    public static class DadosNovoMembro {
        public final String nome;
        public final String email;
        public final String telefone;
        public final String dataNascimento;
        public final int tipoSanguineoId;

        public DadosNovoMembro(String nome, String email, String telefone, String dataNascimento, int tipoSanguineoId) {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
            this.tipoSanguineoId = tipoSanguineoId;
        }
    }

    public static class DadosConquistaInsignia {
        public final int insigniaId;
        public final int membroId;
        public final String dataConquista;

        public DadosConquistaInsignia(int insigniaId, int membroId, String dataConquista) {
            this.insigniaId = insigniaId;
            this.membroId = membroId;
            this.dataConquista = dataConquista;
        }
    }

    public static class DadosConquistaDistintivo {
        public final int distintivoId;
        public final int membroId;
        public final String dataInicio;
        public final String dataFim;

        public DadosConquistaDistintivo(int distintivoId, int membroId, String dataInicio, String dataFim) {
            this.distintivoId = distintivoId;
            this.membroId = membroId;
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
        }
    }

    public static class DadosConquistaEspecialidade {
        public final int especialidadeId;
        public final int membroId;
        public final String dataConquista;

        public DadosConquistaEspecialidade(int especialidadeId, int membroId, String dataConquista) {
            this.especialidadeId = especialidadeId;
            this.membroId = membroId;
            this.dataConquista = dataConquista;
        }
    }
}